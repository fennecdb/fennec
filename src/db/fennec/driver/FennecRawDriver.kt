package db.fennec.driver

import com.codahale.metrics.Meter
import com.google.common.collect.HashMultimap
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.Multimap
import com.google.common.flogger.FluentLogger
import db.fennec.cholla.Cholla
import db.fennec.core.Metrics
import db.fennec.core.Metrics.Companion.FDRIVER_INSERT_REQ
import db.fennec.core.Metrics.Companion.FDRIVER_REMOVE_NS_REQ
import db.fennec.core.Metrics.Companion.FDRIVER_UPSERT_REQ
import db.fennec.fql.*
import db.fennec.gatekeeper.Gatekeeper
import db.fennec.gatekeeper.LocalGatekeeper
import db.fennec.kv.KV
import db.fennec.kv.wiredtiger.WiredTiger
import db.fennec.kv.wiredtiger.WiredTigerKV
import db.fennec.proto.*
import db.fennec.driver.FennecDriverCommons.Companion.createBucket
import db.fennec.driver.FennecDriverCommons.Companion.createLabel
import db.fennec.driver.FennecDriverCommons.Companion.createMetaLabel
import db.fennec.driver.FennecDriverCommons.Companion.toByteArray
import java.util.*

class FennecRawDriver(
        shouldDirectlyOpen: Boolean = false,
        val maxBucketSize: Int = MAX_DEFAULT_BUCKET_SIZE_BYTE,
        val initTimePerBucket: Long = 4000,
        val gatekeeper: Gatekeeper = LocalGatekeeper()

) : FennecDriver {

    private val kv: KV = WiredTigerKV()
    private val maxAvgBucketSize: Int = (maxBucketSize * 0.8).toInt()
    private val cholla: Cholla = Cholla(kv, gatekeeper)

    init {
        if (shouldDirectlyOpen) {
            kv.open()
        }
    }

    override fun open() {
        kv.open()
    }

    override fun close() {
        kv.close()
    }

    override fun reset() {
        kv.reset()
    }

    override fun gc() {
        cholla.run()
    }

    override fun metrics(): EnumMap<DriverMetric, Meter> {
        val result = EnumMap<DriverMetric, Meter>(DriverMetric::class.java)
        result[DriverMetric.QUERY_REQ] = Metrics.FDRIVER_QUERY_REQ
        result[DriverMetric.INSERT_REQ] = Metrics.FDRIVER_INSERT_REQ
        result[DriverMetric.UPSERT_REQ] = Metrics.FDRIVER_UPSERT_REQ
        result[DriverMetric.REMOVE_REQ] = Metrics.FDRIVER_REMOVE_REQ
        result[DriverMetric.REMOVE_NS_REQ] = Metrics.FDRIVER_REMOVE_NS_REQ
        return result
    }

    override fun query(query: FQuery): FResult {
        Metrics.FDRIVER_QUERY_REQ.mark()
        return query(query, false) { key, data -> }
    }

    override fun remove(timerange: LongRange, field: String, ns: String): Long {
        var gotRemoved: Long = 0
        val query: FQuery = FSelection(field, ns, InRange(timerange.first, timerange.last)).toQuery()

        query(query, true) { key: Key, data: List<FDataProto> ->
            val leftOver = ImmutableList.builder<FDataProto>()
            for (d in data) {
                if (timerange.contains(d.timestamp)) {
                    log.atInfo().log("Leaving out: ${d.timestamp} from ${key.field}:${key.ns}")
                    gotRemoved++
                } else {
                    leftOver.add(d)
                }
            }
            kv.put(key, FDataBucket.toProto(leftOver.build()).toByteArray())
        }
        return gotRemoved
    }

    override fun remove(ns: String) {
        FDRIVER_REMOVE_NS_REQ.mark()
        kv.remove(ns)
    }

    private fun query(query: FQuery, shouldCallOp: Boolean, operationPerMatch: (Key, List<FDataProto>) -> Unit): FResult {
        val data = HashMultimap.create<Key, FData>()
        for (selection in query.selections) {
            with (selection) {
                gatekeeper.acquire(field, ns) {
                    // eval query
                    val metaLabel = createMetaLabel(field)
                    val bytes = kv.get(Key(ns = ns, field = metaLabel))
                    val meta = FMetaProto.parseFrom(bytes)
                    val timePerBucket = meta.timePerBucket
                    if (timePerBucket > 0) {
                        val y1 = condition.min()
                        val y2 = condition.max()

                        val list = meta.usedLabelList
                        val toBeLoadedKeys = HashSet<Key>()

                        // map
                        var x1: Long
                        var x2: Long
                        for (bucket in list) {
                            x1 = bucket.timestamp
                            x2 = bucket.timestamp + timePerBucket - 1

                            val isIn = x1 <= y2 && y1 <= x2//x1 <= y1 || x2 <= y2
                            log.atInfo().log("IsIn:$isIn  -> ${bucket.label} ($y1 - $y2), x1=$x1, x2=$x2")
                            if (isIn) {
                                toBeLoadedKeys.add(Key(ns = ns, field = bucket.label))
                            }
                        }
                        // reduce
                        for (key in toBeLoadedKeys) {
                            val entries = FDataBucketProto.parseFrom(kv.get(key))
                            var hasMatched = false
                            for (d in entries.dataList) {
                                if (condition.evaluate(d.timestamp)) {
                                    hasMatched = true
                                    data.put(key, FData.fromProto(d))
                                }
                            }
                            if (hasMatched && shouldCallOp) {
                                operationPerMatch.invoke(key, entries.dataList)
                            }
                        }
                    }
                }
            }
        }
        return FResult(data)
    }

    override fun rebalance(field: String, ns: String) {
        val parsedData = ArrayList<FData>()
        var timePerBucket: Long = 0
        gatekeeper.acquire(field, ns) {
            val metaLabel = createMetaLabel(field)
            val metaKey = Key(ns = ns, field = metaLabel)
            val metaBytes = kv.get(metaKey)
            if (metaBytes != null) {
                val kvMeta = FMetaProto.parseFrom(metaBytes)

                println("kvMeta:${kvMeta.usedLabelList}")
                var numBuckets: Long = 0
                var totalSize: Long = 0
                var maxSize = 0
                var minSize = 0
                val data: Multimap<Key, ByteArray> = HashMultimap.create()
                for (meta in kvMeta.usedLabelList) {
                    val key = Key(ns = ns, field = meta.label)
                    val bytes = kv.get(key)
                    if (bytes != null && bytes.isNotEmpty()) {
                        totalSize += bytes.size
                        if (bytes.size > maxSize) {
                            maxSize = bytes.size
                        }
                        if (bytes.size < minSize) {
                            minSize = bytes.size
                        }
                        data.put(key, bytes)
                        numBuckets++
                    }
                }
                val avgSize = totalSize / numBuckets

                println("avgSize:$avgSize byte, $maxAvgBucketSize max:${maxSize}, min:$minSize, maxBucket:$maxBucketSize")

                // any bucket bigger than max bucket size?
                // or avg bucket size bigger than max avg bucket size?
                if (maxSize > maxBucketSize || avgSize > maxAvgBucketSize) {
                    log.atInfo().log("Rebalancing - max: $maxSize/$maxBucketSize, avg: $avgSize/$maxAvgBucketSize")
                    // TODO backup data map before deleting

                    // clear old data
                    kv.remove(*data.keySet().toTypedArray())
                    // clear meta
                    timePerBucket = kvMeta.timePerBucket * 2
                    kv.put(metaKey, kvMeta.toBuilder().clearUsedLabel().build().toByteArray())

                    // insert new data
                    for (e in data.entries()) {
                        val bucketData = FDataBucketProto.parseFrom(e.value)
                        for (bucketEntry in bucketData.dataList) {
                            parsedData.add(FData.fromProto(bucketEntry))
                        }
                    }
                }
            }
        }
        if (parsedData.isNotEmpty()) {
            log.atInfo().log("Rebalanced data: $parsedData")
            upsert(parsedData, field, ns, timePerBucket)
        }
    }

    override fun listFields(ns: String): Iterable<String> {
        val keys = kv.keys(ns)
        val result = HashSet<String>()
        for (key in keys) {
            val firstColon = key.indexOf(':')
            if (firstColon > 0) {
                result.add(key.substring(0, firstColon))
            }
        }
        return result
    }

    override fun listNS(): Iterable<String> {
        return kv.list()
    }

    override fun insert(data: Iterable<FData>, field: String, ns: String) {
        FDRIVER_INSERT_REQ.mark()
        write(data, false, field, ns)
    }

    override fun upsert(data: Iterable<FData>, field: String, ns: String) {
        FDRIVER_UPSERT_REQ.mark()
        write(data, true, field, ns)
    }

    internal fun insert(data: Iterable<FData>, field: String, ns: String, timePerBucket: Long) {
        write(data, false, field, ns, timePerBucket)
    }

    internal fun upsert(data: Iterable<FData>, field: String, ns: String, timePerBucket: Long) {
        write(data, true, field, ns, timePerBucket)
    }

    private fun write(data: Iterable<FData>, shouldOverwrite: Boolean, field: String, ns: String, timePerBucket: Long? = null) {
        gatekeeper.acquire(field, ns) {
            val metaLabel = createMetaLabel(field)
            val metaKey = Key(ns = ns, field = metaLabel)
            val metaBytes = kv.get(metaKey)

            var usedTimePerBucket = timePerBucket
            if (timePerBucket == null) {
                usedTimePerBucket = if (metaBytes != null && metaBytes.isNotEmpty()) {
                    val metaProto = FMetaProto.parseFrom(metaBytes)
                    metaProto.timePerBucket
                } else {
                    initTimePerBucket
                }
            }
            usedTimePerBucket!!

            val preResult = precompute(data, usedTimePerBucket, field, ns)
            // merge with kv content
            val toBeStored: Map<Key, ByteArray> = merge(preResult, shouldOverwrite, ns)
            // store
            kv.putAll(toBeStored)
            // update meta
            updateMeta(toBeStored, preResult.label2FieldMapping, preResult.label2TimestampMapping, field, ns, usedTimePerBucket)
        }
    }

    private fun updateMeta(toBeStored: Map<Key, ByteArray>, label2FieldMapping: Map<String, String>,
                           label2TimestampMapping: Map<String, Long>, field: String, ns: String, timePerBucket: Long) {
        if (toBeStored.isNotEmpty()) {
            val entry = toBeStored.entries.iterator().next()
            val field = label2FieldMapping[entry.key.field]!!
            val metaLabel = createMetaLabel(field)
            val metaKey = Key(ns = ns, field = metaLabel)

            val data = FMetaProto.newBuilder()
            val kvMetaData = kv.get(metaKey)

            val seen = HashSet<String>()
            for (e in toBeStored.entries) {
                with (e.key.field) {
                    if (!seen.contains(this)) {
                        seen.add(this)
                        data.addUsedLabel(createFMetaLabel(this, label2TimestampMapping[this]!!))
                    }
                }
            }
            if (kvMetaData != null) {
                val kvMeta = FMetaProto.parseFrom(kvMetaData)
                for (kvMLabel in kvMeta.usedLabelList) {
                    if (!seen.contains(kvMLabel.label)) {
                        seen.add(kvMLabel.label)
                        data.addUsedLabel(kvMLabel)
                    }
                }
            }
            data.timePerBucket = timePerBucket
            kv.put(metaKey, data.build().toByteArray())
        }
    }

    private fun createFMetaLabel(label: String, timestamp: Long): FMetaLabelProto {
        return FMetaLabelProto.newBuilder()
                .setLabel(label)
                .setTimestamp(timestamp)
                .build()
    }

    private fun merge(preResult: PreResult, shouldOverwrite: Boolean, ns: String): Map<Key, ByteArray> {
        val result = ImmutableMap.builder<Key, ByteArray>()

        val transformedContent = preResult.transformedContent
        val kvContent = preResult.kvContent

        for (label in transformedContent.keySet()) {
            val fDataList = transformedContent[label]
            val currentKey = Key(ns = ns, field = label)
            log.atInfo().log("Storing: $currentKey")

            if (kvContent.containsKey(label)) {
                // merge logic, merge new and old data
                val entries: FDataBucket = kvContent[label]!!
                val kvFData = entries.entries

                val mergedList = ArrayList<FData>()
                val seenTimestamp = HashSet<Long>()

                if (shouldOverwrite) { // update (first new, last old)
                    combine(fDataList, kvFData, seenTimestamp, mergedList)
                } else { // insert (first old, last new)
                    combine(kvFData, fDataList, seenTimestamp, mergedList)
                }
                result.put(currentKey, mergedList.toByteArray())
            } else { // build new
                result.put(currentKey, fDataList.toByteArray())
            }
        }
        return result.build()
    }

    private fun combine(listA: Iterable<FData>, listB: Iterable<FData>, seenTimestamp: HashSet<Long>, mergedList: ArrayList<FData>) {
        for (elA in listA) {
            seenTimestamp.add(elA.timestamp)
            mergedList.add(elA)
        }
        for (elB in listB) {
            if (!seenTimestamp.contains(elB.timestamp)) {
                mergedList.add(elB)
            }
        }
    }

    private fun precompute(inputData: Iterable<FData>, timePerBucket: Long, field: String, ns: String): PreResult {
        val loadedLabels = HashSet<String>()
        val transformedContent = HashMultimap.create<String, FData>()
        val kvContent = ImmutableMap.builder<String, FDataBucket>()
        val label2FieldMapping = ImmutableMap.builder<String, String>()
        val label2TimestampMapping = ImmutableMap.builder<String, Long>()
        val seenLabels = HashSet<String>()

        for (d in inputData) {
            val bucket = createBucket(d.timestamp, timePerBucket)
            val label = createLabel(field, bucket)
            transformedContent.put(label, d)
            if (!seenLabels.contains(label)) {
                seenLabels.add(label)
                label2FieldMapping.put(label, field)
                label2TimestampMapping.put(label, bucket)
            }

            // preload kv content for all labels
            if (!loadedLabels.contains(label)) {
                loadedLabels.add(label)
                val bytes = kv.get(Key(ns = ns, field = label))
                if (bytes != null) {
                    val entries = FDataBucketProto.parseFrom(bytes)
                    kvContent.put(label, FDataBucket.fromProto(entries))
                }
            }
        }
        return PreResult(transformedContent, kvContent.build(), label2FieldMapping.build(), label2TimestampMapping.build())
    }

    data class PreResult(val transformedContent: Multimap<String, FData>, val kvContent: Map<String, FDataBucket>,
                         val label2FieldMapping: Map<String , String>, val label2TimestampMapping: Map<String, Long>)

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

        @JvmStatic private val SIZE_MB = 1024 * 1024
        @JvmStatic private val MAX_DEFAULT_BUCKET_SIZE_BYTE = 32 * SIZE_MB
    }
}

fun main(args: Array<String>) {
    WiredTiger.loadLibrary()
    val ns = "test"

    WiredTigerKV(true).use {
        it.remove(Key(ns = ns, field = "x:d4:meta"))
    }
    FennecRawDriver(true).use { driver ->
        val data = ArrayList<FData>()
        data.add(FData(3.0, 1544912246000))
        data.add(FData(4.0, 1544912248000))
        data.add(FData(5.0, 1544912251000))
        driver.insert(data, "x", ns)

        for (i in 0..500) {
            val start = System.nanoTime()
            val query: FQuery = FSelection("x", ns, InRange(0L, 1544912246000L)).toQuery()
            val result = driver.query(query)
            val end = (System.nanoTime() - start) / 1000000.0
            println("end:$end")
            println(query)
            println(result)
        }
    }
    WiredTigerKV(true).use { kv ->
        val payload = kv.get(Key(ns = ns, field = "x:d4:1544912244000"))
        val entries = FDataBucketProto.parseFrom(payload)
        println("${entries.dataList.size}, data:'${entries}'")
    }
}