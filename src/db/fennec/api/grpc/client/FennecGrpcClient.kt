package db.fennec.api.grpc.client

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.RateLimiter
import db.fennec.api.proto.*
import db.fennec.fql.FQuery
import db.fennec.fql.FSelection
import db.fennec.fql.InRange
import db.fennec.kv.Key
import db.fennec.proto.FDataBucketProto
import db.fennec.proto.FDataEntryProto
import db.fennec.proto.FDataProto
import db.fennec.fql.FData
import db.fennec.fql.FResult
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import java.time.Instant
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

internal class FennecGrpcClient(val host: String = "localhost", val port: Int = 64733) {

    private var channel: ManagedChannel? = null
    private var stub: FennecServiceGrpc.FennecServiceBlockingStub? = null

    fun connect() {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build()
        stub = FennecServiceGrpc.newBlockingStub(channel)
    }

    private fun List<FDataProto>.toNonProto(): List<FData> {
        return stream()
                .map { d -> FData.fromProto(d) }
                .collect(Collectors.toList())
    }

    fun query(query: FQuery): FResult {
        val result = HashMultimap.create<Key, FData>()
        for (selection in query.selections) {
            with (selection) {
                val resp = stub.deadline()?.query(FQueryReq.newBuilder()
                        .setField(field)
                        .setNamespace(ns)
                        .setInRangeLow(condition.min())
                        .setInRangeHigh(condition.max())
                        .build())
                if (resp != null) {
//                    resp.status
                    val fResultProto = resp.result

                    for (e: FDataEntryProto in resp.result.dataList) {
//                        result.putAll(Key(ns, e.field), e.toNonProto())
                    }
                }
            }
        }
        return FResult(result)
    }

    private fun FennecServiceGrpc.FennecServiceBlockingStub?.deadline(): FennecServiceGrpc.FennecServiceBlockingStub? {
        return this?.withDeadlineAfter(DEADLINE_SEC, TimeUnit.SECONDS)
    }

    fun insert(data: Iterable<FData>, field: String, ns: String) {
        val resp = stub.deadline()?.insert(buildWriteReq(data, field, ns))
        log.atInfo().log("Insert:$resp")
    }

    fun upsert(data: Iterable<FData>, field: String, ns: String) {
        val resp = stub.deadline()?.upsert(buildWriteReq(data, field, ns))
        log.atInfo().log("Upsert:$resp")
    }

    private fun buildWriteReq(data: Iterable<FData>, field: String, ns: String): FWriteReq {
        val request = FWriteReq.newBuilder()
        for (d in data) {
            request.addPayload(FDataEntryProto.newBuilder()
                    .setField(field)
                    .setNamespace(ns)
                    .setData(d.toProto())
                    .build()
            )
        }
        return request.build()
    }

    fun remove(lowRange: Long, highRange: Long, field: String, ns: String) {
        val resp = stub.deadline()?.remove(FRemoveReq.newBuilder()
                .setField(field)
                .setNamespace(ns)
                .setInRangeLow(lowRange)
                .setInRangeHigh(highRange)
                .build())
        log.atInfo().log("Remove:$resp")
    }

    fun removeNamespace(ns: String) {
        val resp = stub.deadline()?.removeNS(FRemoveNSReq.newBuilder().setNamespace(ns).build())
        log.atInfo().log("RemoveNS:$resp")
    }

    fun close() {
        channel?.shutdown()
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

        private val DEADLINE_SEC: Long = 15

        data class Key(val field: String, val ns: String)
    }
}

fun main(args: Array<String>) {
//    val log = FluentLogger.forEnclosingClass()
//    val ns = "fennec_client_test"
////    val rateLimiter = RateLimiter.create(1.0)
////
//////    while (true) {
////        for (i in 0..1000) {
////            val sin = Math.sin(0.01 * i)
////            println("sin:$sin")
////        }
//////    }
////
////    return
//
//    val client = FennecGrpcClient("127.0.0.1", 64733)
//
//    log.atInfo().log("Connecting...")
//    client.connect()
//    log.atInfo().log("Connected.")
//
//    val rateLimiter = RateLimiter.create(1.0)
//    var i: Long = 0
//    while (true) {
//        val value = Math.sin(i * 0.01) * 100
//        log.atInfo().log("Insert $value...")
//        val data = HashMultimap.create<String, FData>()
//        //Random().nextInt((500 + 1) - 10) + 10 * 1.0
//        data.put("x", FData(value, Instant.now().toEpochMilli()))
//        var succeeded = false
//        while (!succeeded) {
//            try {
//                rateLimiter.acquire()
//
//                client.insert(data, ns)
//                succeeded = true
//            } catch (e: StatusRuntimeException) {
//                log.atWarning().log("Insert failed")
//            }
//        }
//        log.atInfo().log("Inserted.")
//        i += 5
//    }
////    data.put("x", FData(3.0, 1544912246000))
////    data.put("x", FData(4.0, 1544912248000))
////    data.put("x", FData(5.0, 1544912251000))
//
//
//    val start = System.nanoTime()
//    val query: FQuery = FSelection("x", ns, InRange(0L, 1544912246000L)).toQuery()
//    val result = client.query(query)
//    val end = (System.nanoTime() - start) / 1000000.0
//    println("end:$end")
//    println(query)
//    println(result)
}