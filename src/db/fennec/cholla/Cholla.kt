package db.fennec.cholla

import com.google.common.collect.ImmutableSet
import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.RateLimiter
import db.fennec.fql.Key
import db.fennec.gatekeeper.Gatekeeper
import db.fennec.gatekeeper.LocalGatekeeper
import db.fennec.kv.KV
import db.fennec.common.LogDefinition.Companion.config
import db.fennec.proto.FMetaProto

class Cholla(val kv: KV, val gatekeeper: Gatekeeper = LocalGatekeeper()) : Runnable {

    override fun run() {
       runWithStatus()
    }

    internal fun runWithStatus(nsWhitelist: Set<String> = setOf()): ChoStatus {
        RATE_LIMITER.acquire()
        if (!kv.isOpen()) {
            kv.open()
        }

        val namespaces = kv.list()

        val removed = ImmutableSet.builder<Key>()
        for (ns in namespaces) {
            if (nsWhitelist.isEmpty() || (nsWhitelist.isNotEmpty() && nsWhitelist.contains(ns))) {
                processNS(ns, removed)
            }
        }
        return ChoStatus(removed = removed.build())
    }

    private fun processNS(ns: String, removed: ImmutableSet.Builder<Key>) {
        val metaKeys = HashSet<String>()
        val keys = kv.keys(ns)
        for (key in keys) {
            if (key.endsWith("meta")) {
                metaKeys.add(key)
            }
        }

        if (metaKeys.isNotEmpty()) {
            for (metaKey in metaKeys) {
                cleanupMeta(ns, rawMetaKey = metaKey, removed = removed)
            }
        }
    }

    private fun cleanupMeta(ns: String, rawMetaKey: String, removed: ImmutableSet.Builder<Key>) {
        val metaKey = Key(ns = ns, field = rawMetaKey)
        val bytes = kv.get(metaKey)
        val metaProto = FMetaProto.parseFrom(bytes)
        val colonIndex = rawMetaKey.indexOf(':')
        if (colonIndex > 0) {
            val fieldPrefix = rawMetaKey.substring(0, colonIndex)
            gatekeeper.acquire(fieldPrefix, ns) {
                val filteredSuffixes = ArrayList<Long>()
                for (suffix in metaProto.usedLabelSuffixList) {
                    val field = "$fieldPrefix:$suffix"
                    val bucketBytes = kv.get(Key(ns = ns, field = field))

                    if (bucketBytes!!.isEmpty()) {
                        val key = Key(ns = ns, field = field)
                        removed.add(key)
                        kv.remove(Key(ns = ns, field = field))
                    } else {
                        filteredSuffixes.add(suffix)
                    }
                }
                kv.put(metaKey, metaProto.toBuilder()
                        .clearUsedLabelSuffix()
                        .addAllUsedLabelSuffix(filteredSuffixes)
                        .build()
                        .toByteArray()
                )
            }
        }
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass().config()

        private val RATE_LIMITER = RateLimiter.create(1.0 / (60 * 60))
    }
}