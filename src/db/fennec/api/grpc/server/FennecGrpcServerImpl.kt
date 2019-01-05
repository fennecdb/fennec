package db.fennec.api.grpc.server

import com.google.common.collect.HashMultimap
import com.google.common.flogger.FluentLogger
import db.fennec.api.proto.*
import db.fennec.error.FennecServerException
import db.fennec.error.FennecExternalExceptionException
import db.fennec.error.FennecInternalException
import db.fennec.error.Status
import db.fennec.fql.*
import db.fennec.proto.FDataEntryProto
import db.fennec.proto.FResultProto
import db.fennec.timeseries.driver.FennecDriver
import io.grpc.stub.StreamObserver

open class FennecGrpcServerImpl(val driver: FennecDriver) : FennecServiceGrpc.FennecServiceImplBase() {

    fun <T> reply(observer: StreamObserver<T>, handleFailure: (FennecServerException) -> T, body: () -> T) {
        with (observer) {
            try {
                val response = body.invoke()
                onNext(response)
            } catch (e: FennecInternalException) {
                // don't send internal exceptions
                log.atSevere().withCause(e).log("Fennec service encountered an internal exception")
            } catch (e: FennecExternalExceptionException) {
                log.atSevere().withCause(e).log("Fennec service encountered an external exception")
                onNext(handleFailure.invoke(e))
            } finally {
                onCompleted()
            }
        }
    }

    fun close() {
        driver.close()
    }

    override fun query(request: FQueryReq, observer: StreamObserver<FQueryResp>) {
        log.atFine().log("Received query...")
        reply(observer, {
            FQueryResp.newBuilder().setStatus(handleFail(it)).build()
        }) {
            with(request) {
                val query: FQuery = FSelection(field, namespace, InRange(inRangeLow, inRangeHigh)).toQuery()
                val result = driver.query(query)
                // convert to FDataEntries
                val resultProto = FResultProto.newBuilder()

                for (entry in result.data.entries()) {
                    resultProto.addData(FDataEntryProto.newBuilder()
                            .setField(entry.key.field)
                            .setNamespace(entry.key.ns)
                            .setData(entry.value.toProto())
                            .build()
                    )
                }
                FQueryResp.newBuilder()
                        .setStatus(OK)
                        .setResult(resultProto.build())
                        .build()
            }
        }
        log.atFine().log("Finished query.")
    }

    override fun insert(request: FWriteReq, observer: StreamObserver<FStatus>) {
        write(request, observer, false)
    }

    override fun upsert(request: FWriteReq, observer: StreamObserver<FStatus>) {
        write(request, observer, true)
    }

    private fun write(request: FWriteReq, observer: StreamObserver<FStatus>, shouldUpdate: Boolean) {
        log.atFine().log("Received write(update:$shouldUpdate)...")
        reply(observer, ::handleFail) {
//            val ns = nsFromProto(request.)
            val payload: List<FDataEntryProto> = request.payloadList
            val data = HashMultimap.create<Key, FData>()
            for (e in payload) {
                data.put(Key(ns = e.namespace, field = e.field), FData.fromProto(e.data))
            }
            for (key in data.keySet()) {
                val dataList: Set<FData> = data[key]

                if (shouldUpdate) {
                    driver.upsert(dataList, key.field, key.ns)
                } else {
                    driver.insert(dataList, key.field, key.ns)
                }
            }
            OK
        }
        log.atFine().log("Finished write(update:$shouldUpdate).")
    }

    override fun removeNS(request: FRemoveNSReq, observer: StreamObserver<FStatus>) {
        log.atFine().log("Received remove ns...")
        reply(observer, ::handleFail) {
            driver.remove(request.namespace)
            OK
        }
        log.atFine().log("Finished remove ns.")
    }

    override fun remove(request: FRemoveReq, observer: StreamObserver<FStatus>) {
        log.atFine().log("Received remove...")
        reply(observer, ::handleFail) {
            with (request) {
                driver.remove(LongRange(inRangeLow, inRangeHigh), field, namespace)
                OK
            }
        }
        log.atFine().log("Finished remove.")
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

        private val OK = FStatus.newBuilder()
                .setMessage(Status.OK.getDescriptor())
                .setStatusCode(Status.OK.getCode())
                .build()

        fun handleFail(e: FennecServerException): FStatus {
            return FStatus.newBuilder()
                    .setMessage(e.structuredMsg)
                    .setStatusCode(e.status.getCode())
                    .build()
        }
    }
}