package db.fennec.driver

import com.codahale.metrics.Timer
import com.google.common.collect.HashMultimap
import com.google.common.collect.ImmutableSet
import com.google.common.collect.Multimap
import com.google.common.flogger.FluentLogger
import db.fennec.fql.*
import db.fennec.kv.KV
import db.fennec.kv.wiredtiger.WiredTigerKV
import db.fennec.metrics.Snap
import db.fennec.proto.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.TimeUnit

class FennecRawDriverTest {

    private val field = "abc"
    private val dOne = FData(3.0, 1544912246000)
    private val dTwo = FData(4.0, 1544912248000)
    private val dThree = FData(5.0, 1544912251000)
    private val dFour = FData(3.0, 1544912246030)
    private val dFive = FData(4.0, 1544912246030)
    private val keyOne = Key(ns = TEST_NS, field = "abc:1544912244000")
    private val keyTwo = Key(ns = TEST_NS, field = "abc:1544912248000")
    private val metaKey = Key(ns = TEST_NS, field = "abc:meta")

    @After
    fun after() {
        FennecRawDriver(true).use { driver ->
            driver.remove(TEST_NS)
        }
    }

    @Test
    fun testMetaParsing() {
        WiredTigerKV(true).use {
            val builder = FMetaProto.newBuilder()
            with (builder) {
                for (i in 0..500_000) {
                    addUsedLabelSuffix(1234567890L + i )
                }
                field = "test"
                timePerBucket = 4000
            }
            val bytes = builder.build().toByteString()

            log.atInfo().log("Parsing...")
            val timer = Timer()
            for (i in 0..500) {
                timer.time {
                    FMetaProto.parseFrom(bytes)
                }
            }
            val snap = Snap(timer.snapshot, TimeUnit.MILLISECONDS)
            log.atInfo().log("Timing:$snap")
            assertTrue("Meta deserialization not fast enough", snap.mean() < 15)
        }
    }

    @Test
    fun testSmallQuery() {
        FennecRawDriver(true).use { driver ->
            driver.upsert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            val queryOne: FQuery = FSelection(field, TEST_NS, InRange(1544912246020, 1544912246030)).toQuery()
            val wantedOne = HashMultimap.create<Key, FData>()
            wantedOne.put(keyOne, dFour)
            checkQuery(driver, queryOne, wantedOne)
        }
        queryCleanup()
    }

    @Test
    fun testMediumQuery() {
        FennecRawDriver(true).use { driver ->
            driver.upsert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            val queryTwo: FQuery = FSelection(field, TEST_NS, InRange(1544912246000, 1546000000000)).toQuery()
            val wantedTwo = HashMultimap.create<Key, FData>()
            wantedTwo.put(keyOne, dOne)
            wantedTwo.put(keyOne, dFour)
            wantedTwo.put(keyTwo, dTwo)
            wantedTwo.put(keyTwo, dThree)

            checkQuery(driver, queryTwo, wantedTwo)
        }
        queryCleanup()
    }

    @Test
    fun testRebalance() {
        FennecRawDriver(true, 35).use { driver ->
            driver.upsert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            driver.rebalance(field, TEST_NS)
        }
    }

    private fun queryCleanup() {
        WiredTigerKV(true).use { kv ->
            kv.remove(metaKey)
            kv.remove(keyOne)
            kv.remove(keyTwo)
        }
    }

    private fun checkQuery(driver: FennecRawDriver, query: FQuery, wanted: Multimap<Key, FData>) {
        val result = driver.query(query)

        println("Wanted:$wanted vs\nIs:$result")
        assertEquals("Incorrect number of results", wanted.size(), result.data.size())

        for (wKey in wanted.keySet()) {
            val values = result.data.get(wKey).toSet()
            assertEquals("Incorrect number of values for key '${wanted.get(wKey)}'", wanted.get(wKey).size, values.size)
            for (wVal in wanted.get(wKey)) {
                assertTrue("Result does not contain $wVal", values.contains(wVal))
            }
        }
    }

    @Test
    fun testRemove() {
        FennecRawDriver(true).use { driver ->
            driver.upsert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            val removedA = driver.remove(LongRange(dFour.timestamp - 1, dFour.timestamp), field, TEST_NS)
            assertEquals("Incorrect incorrect number of values", 1, removedA)
            val removedB = driver.remove(LongRange(dFour.timestamp - 1, dFour.timestamp), field, TEST_NS)
            assertEquals("Should not be able to remove anything", 0, removedB)
        }

        WiredTigerKV(true).use { kv ->
            checkEntries(kv, keyOne, 1, dOne)
        }
    }

    @Test
    fun testRemoveNS() {

    }

    @Test
    fun testUpsert() {
        FennecRawDriver(true).use { driver ->
            driver.upsert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            driver.upsert(iterable(dFive), field, TEST_NS)
        }
        // check data manually
        WiredTigerKV(true).use { kv ->
            checkEntries(kv, keyOne, 2, dOne, dFive)
            checkEntries(kv, keyTwo, 2, dTwo, dThree)
            checkMeta(kv, metaKey, 1544912244000, 1544912248000)
        }
    }

    @Test
    fun testInsert() {
        FennecRawDriver(true).use { driver ->
            driver.insert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            // following insert should not modify anything as it overlaps with dFour
            driver.insert(iterable(dFive), field, TEST_NS)
        }
        // check data manually
        WiredTigerKV(true).use { kv ->
            checkEntries(kv, keyOne, 2, dOne, dFour)
            checkEntries(kv, keyTwo, 2, dTwo, dThree)
            checkMeta(kv, metaKey,1544912244000, 1544912248000)
        }
    }

    @Test
    fun testListNS() {
        FennecRawDriver(true).use { driver ->
            driver.insert(iterable(dOne, dTwo, dThree, dFour), field, TEST_NS)
            val namespaces = driver.listNS().toSet()
            log.atInfo().log("Namespaces:$namespaces")
            assertTrue("Namespaces does not contain $TEST_NS", namespaces.contains(TEST_NS))
        }
    }

    private fun <T> iterable(vararg value: T): Iterable<T> {
        val data = ArrayList<T>()
        for (v in value.iterator()) {
            data.add(v)
        }
        return data
    }

    private fun checkMeta(kv: KV, metaKey: Key, vararg wanted: Long) {
        log.atInfo().log("meta:$metaKey")
        val meta = FMetaProto.parseFrom(kv.get(metaKey))
        log.atInfo().log("meta:$meta")

        val isMeta = meta.usedLabelSuffixList.toSet()
        for (w in wanted) {
            assertTrue("Meta $isMeta does not contain $w", isMeta.contains(w))
        }
        kv.remove(metaKey)
    }

    private fun checkEntries(kv: KV, key: Key, numEntriesExpected: Int, vararg wanted: FData) {
        val bytes = kv.get(key)
        val bucket = FDataBucketProto.parseFrom(bytes)
        val set: Set<FData> = bucket.dataList.stream()
                .map { FData.fromProto(it) }
                .collect(ImmutableSet.toImmutableSet<FData>())
        log.atInfo().log("entries:$set")

        assertEquals("Incorrect number of entries", numEntriesExpected, set.size)
        for (w in wanted) {
            assertTrue("Entries '$bucket' does not contain '$w'", set.contains(w))
        }
        kv.remove(key)
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

        private val TEST_NS = "fennec_raw_driver_test"
    }

}
