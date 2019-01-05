package db.fennec.timeseries.driver

import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.RateLimiter
import com.google.common.util.concurrent.Uninterruptibles
import db.fennec.fql.Key
import db.fennec.gatekeeper.Gatekeeper
import db.fennec.gatekeeper.LocalGatekeeper
import db.fennec.kv.KV
import db.fennec.proto.FMetaLabelProto
import db.fennec.proto.FMetaProto
import io.atomix.protocols.raft.MultiRaftProtocol
import io.atomix.protocols.raft.ReadConsistency
import io.atomix.core.Atomix
import io.atomix.protocols.raft.partition.RaftPartitionGroup
import io.atomix.utils.net.Address

class DriverGC(val kv: KV, val gatekeeper: Gatekeeper = LocalGatekeeper()) : Runnable {

    override fun run() {
        RATE_LIMITER.acquire()
        val namespaces = kv.list()

        for (ns in namespaces) {
//            println("ns:$ns")
            val metaKeys = HashSet<String>()
            val keys = kv.keys(ns)
            for (key in keys) {
//                println("> $key")
                if (key.endsWith("meta")) {
                    metaKeys.add(key)
                }
            }

            if (metaKeys.isNotEmpty()) {
                println("meta:$metaKeys, ns:$ns")
                for (metaKey in metaKeys) {
                    cleanupMeta(ns, rawMetaKey = metaKey)
                }
            }
        }
    }

    private fun cleanupMeta(ns: String, rawMetaKey: String) {
        val metaKey = Key(ns = ns, field = rawMetaKey)
        val bytes = kv.get(metaKey)
        val metaProto = FMetaProto.parseFrom(bytes)
        val colonIndex = rawMetaKey.indexOf(':')
        if (colonIndex > 0) {
            val field = rawMetaKey.substring(0, colonIndex)
            gatekeeper.acquire(field, ns) {
                val filteredLabels = ArrayList<FMetaLabelProto>()
                for (usedLabel in metaProto.usedLabelList) {
                    val bucketBytes = kv.get(Key(ns = ns, field = usedLabel.label))
                    println(usedLabel.label + " = ${bucketBytes?.size}")
                    if (bucketBytes!!.isEmpty()) {
                        kv.remove(Key(ns = ns, field = field))
                    } else {
                        filteredLabels.add(usedLabel)
                    }
                }
                kv.put(metaKey,
                        metaProto.toBuilder()
                                .clearUsedLabel()
                                .addAllUsedLabel(filteredLabels)
                                .build()
                                .toByteArray()
                )
            }
        }
    }

    companion object {
        private val RATE_LIMITER = RateLimiter.create(1.0 / 60 * 3)
    }
}

fun main(args: Array<String>) {
    val log = FluentLogger.forEnclosingClass()
    val atomix = Atomix.builder()
            .withMemberId("member1")
            .withAddress(Address("localhost", 12345))
            .withManagementGroup(RaftPartitionGroup.builder("system")
                    .withNumPartitions(1)
                    .withMembers("member1")
                    .build())
            .withPartitionGroups(RaftPartitionGroup.builder("raft")
                    .withPartitionSize(1)
                    .withNumPartitions(1)
                    .withMembers("member1")
                    .build())
            .build()
    log.atInfo().log("joining...")
//    atomix.
    val future = atomix.start()

    log.atInfo().log("here: $future")
    val uninterruptibly = Uninterruptibles.getUninterruptibly(future)

    log.atInfo().log("joined")
    val lock = atomix.lockBuilder("my-lock")
            .withProtocol(MultiRaftProtocol.builder()
                    .withReadConsistency(ReadConsistency.LINEARIZABLE)
                    .build())
            .build()


    lock.lock()
    try {
        log.atInfo().log("in lock")
    } finally {
        lock.unlock()
    }

    log.atInfo().log("end")
    atomix.stop()
//    DriverGC(WiredTigerKV(true)).run()
}