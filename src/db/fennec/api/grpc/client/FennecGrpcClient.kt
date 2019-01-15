package db.fennec.api.grpc.client

import com.google.common.collect.HashMultimap
import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.Uninterruptibles
import db.fennec.api.grpc.client.error.FennecException
import db.fennec.api.proto.*
import db.fennec.fql.FQuery
import db.fennec.proto.FDataEntryProto
import db.fennec.proto.FDataProto
import db.fennec.fql.FData
import db.fennec.fql.FResult
import db.fennec.fql.Key
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import db.fennec.common.LogDefinition.Companion.config
import java.util.logging.Level

internal class FennecGrpcClient(val host: String = "localhost", val port: Int = 64733, val level: Level = Level.WARNING) {

    private var channel: ManagedChannel? = null
    private var stub: FennecServiceGrpc.FennecServiceBlockingStub? = null

    init {
        log.config(level)
    }

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

    private fun FDataEntryProto.toNonProto(): FData {
        return FData.fromProto(this.data)
    }

    fun query(query: FQuery): FResult? {
        return handle {
            val result = HashMultimap.create<Key, FData>()
            for (selection in query.selections) {
                with (selection) {
                    val resp = stub.deadline()?.query(FQueryReq.newBuilder()
                            .setField(field)
                            .setNamespace(ns)
                            .setInRangeLow(condition.min())
                            .setInRangeHigh(condition.max())
                            .build())
                    log.atInfo().log("$resp")
                    if (resp != null) {
                        for (e: FDataEntryProto in resp.result.dataList) {
                            val colonIndex = e.field.indexOf(':')
                            if (colonIndex >= 0) {
                                result.put(Key(field = e.field.substring(0, colonIndex), ns = ns), e.toNonProto())

                            }
                        }
                    }
                }
            }
            FResult(result)
        }
    }

    private fun FennecServiceGrpc.FennecServiceBlockingStub?.deadline(): FennecServiceGrpc.FennecServiceBlockingStub? {
        return this?.withDeadlineAfter(DEADLINE_SEC, TimeUnit.SECONDS)
    }

    private fun <T> handle(body: () -> T): T? {
        var numAttempt = 0
        val maxRetries = 50
        var retry = true
        var result: T? = null
        while (retry) {
            try {
                retry = false
                numAttempt++
                result = body.invoke()
            } catch (e: StatusRuntimeException) {

                if (numAttempt >= maxRetries) {
                    throw FennecException(-1, e.message)
                }
                retry = true
                log.atWarning().withCause(e).log("Encountered '${e.status}' will retry (attempt $numAttempt)...")
                Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS)
            }
        }
        return result
    }

    fun insert(data: Iterable<FData>, field: String, ns: String) {
        handle {
            val resp = stub.deadline()?.insert(buildWriteReq(data, field, ns))
            log.atInfo().log("Insert:$resp")
        }
    }

    fun upsert(data: Iterable<FData>, field: String, ns: String) {
        handle {
            val resp = stub.deadline()?.upsert(buildWriteReq(data, field, ns))
            log.atInfo().log("Upsert:$resp")
        }
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
        handle {
            val resp = stub.deadline()?.remove(FRemoveReq.newBuilder()
                    .setField(field)
                    .setNamespace(ns)
                    .setInRangeLow(lowRange)
                    .setInRangeHigh(highRange)
                    .build())
            log.atInfo().log("Remove:$resp")
        }
    }

    fun removeNamespace(ns: String) {
        handle {
            val resp = stub.deadline()?.removeNS(FRemoveNSReq.newBuilder().setNamespace(ns).build())
            log.atInfo().log("RemoveNS:$resp")
        }
    }

    fun close() {
        channel?.shutdown()
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass().config()

        private val DEADLINE_SEC: Long = 15
    }
}