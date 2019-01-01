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
import java.util.stream.Collectors

class FennecGrpcClient(val host: String, val port: Int) {

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

    private fun Multimap<String, FData>.toEntries(): FDataBucketProto {
        val dataEntries = FDataBucketProto.newBuilder()
        for (e in entries()) {
            dataEntries.addData(FDataProto.newBuilder()
                    .setValue(e.value.value)
                    .setTimestamp(e.value.timestamp)
                    .build()
            )
        }
        return dataEntries.build()
    }

    fun query(query: FQuery): FResult {
        val result = HashMultimap.create<Key, FData>()
        for (selection in query.selections) {
            with (selection) {
                val resp = stub?.query(FQueryReq.newBuilder()
                        .setField(field)
                        .setNamespace(ns)
                        .setInRangeLow(condition.min())
                        .setInRangeHigh(condition.max())
                        .build())
                if (resp != null) {
//                    resp.status
                    val fResultProto = resp.result

//                    for (e in resp) {
//                        result.putAll(Key(ns, e.field), e.dataList.toNonProto())
//                    }
                }
            }
        }
        return FResult(result)
    }

    private fun buildWriteReq(data: Multimap<String, FData>, ns: String): FWriteReq {
        val request = FWriteReq.newBuilder()
        for (d in data.entries()) {
            request.addPayload(FDataEntryProto.newBuilder()
                    .setField(d.key)
                    .setNamespace(ns)
                    .setData(d.value.toProto())
                    .build()
            )
        }
        return request.build()
    }

    fun insert(data: Multimap<String, FData>, ns: String) {
        val resp = stub?.insert(buildWriteReq(data, ns))
        log.atInfo().log("Insert:$resp")
    }

    fun upsert(data: Multimap<String, FData>, ns: String) {
        val resp = stub?.upsert(buildWriteReq(data, ns))
        log.atInfo().log("Upsert:$resp")
    }

    fun remove(timerange: LongRange, field: String, ns: String) {
        val resp = stub?.remove(FRemoveReq.newBuilder()
                .setField(field)
                .setNamespace(ns)
                .setInRangeLow(timerange.first)
                .setInRangeHigh(timerange.last)
                .build())
        log.atInfo().log("Remove:$resp")
    }

    fun remove(ns: String) {
        val resp = stub?.removeNS(FRemoveNSReq.newBuilder().setNamespace(ns).build())
        log.atInfo().log("RemoveNS:$resp")
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

//        private val DEADLINE_SEC: Long = 15
    }
}

fun main(args: Array<String>) {
    val log = FluentLogger.forEnclosingClass()
    val ns = "fennec_client_test"
//    val rateLimiter = RateLimiter.create(1.0)
//
////    while (true) {
//        for (i in 0..1000) {
//            val sin = Math.sin(0.01 * i)
//            println("sin:$sin")
//        }
////    }
//
//    return

    val client = FennecGrpcClient("127.0.0.1", 64733)

    log.atInfo().log("Connecting...")
    client.connect()
    log.atInfo().log("Connected.")

    val rateLimiter = RateLimiter.create(1.0)
    var i: Long = 0
    while (true) {
        val value = Math.sin(i * 0.01) * 100
        log.atInfo().log("Insert $value...")
        val data = HashMultimap.create<String, FData>()
        //Random().nextInt((500 + 1) - 10) + 10 * 1.0
        data.put("x", FData(value, Instant.now().toEpochMilli()))
        var succeeded = false
        while (!succeeded) {
            try {
                rateLimiter.acquire()

                client.insert(data, ns)
                succeeded = true
            } catch (e: StatusRuntimeException) {
                log.atWarning().log("Insert failed")
            }
        }
        log.atInfo().log("Inserted.")
        i += 5
    }
//    data.put("x", FData(3.0, 1544912246000))
//    data.put("x", FData(4.0, 1544912248000))
//    data.put("x", FData(5.0, 1544912251000))


    val start = System.nanoTime()
    val query: FQuery = FSelection("x", ns, InRange(0L, 1544912246000L)).toQuery()
    val result = client.query(query)
    val end = (System.nanoTime() - start) / 1000000.0
    println("end:$end")
    println(query)
    println(result)
}