package db.fennec.remote

import db.fennec.kv.operator.*
import db.fennec.kv.operator.raw.KVOperator
import io.grpc.stub.StreamObserver

open class KVRemoteProcedureProvider : db.fennec.proto.PantheraServiceGrpc.PantheraServiceImplBase() {

//    private val operator: KVOperator = KVRawOperator()

//    companion object {
//        fun errorExit() {
//            System.err.println("> Panthera encountered an illegal state, shutting down.")
//            System.exit(1)
//        }
//    }
//
//    fun <T> reply(responseObserver: StreamObserver<T>, response: T) {
//        responseObserver.onNext(response)
//        responseObserver.onCompleted()
//    }
//
//    override fun get(request: db.fennec.proto.PantheraGetRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraGetResponse>) {
//        reply(responseObserver, operator.get(request))
//    }
//
//    override fun getMulti(request: db.fennec.proto.PantheraGetMultiRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraGetMultiResponse>) {
//        reply(responseObserver, operator.getMulti(request))
//    }
//
//    override fun getKeys(request: db.fennec.proto.PantheraGetKeysRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraGetKeysResponse>) {
//        reply(responseObserver, operator.getKeys(request))
//    }
//
//    override fun getKeySlice(request: db.fennec.proto.PantheraGetKeySliceRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraGetKeysResponse>) {
//        reply(responseObserver, operator.getKeySlice(request))
//    }
//
//    override fun insert(request: db.fennec.proto.PantheraOversertRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) {
//        reply(responseObserver, operator.insert(request))
//    }
//
//    override fun overwrite(request: db.fennec.proto.PantheraOversertRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) {
//        reply(responseObserver, operator.overwrite(request))
//    }
//
//    override fun size(request: db.fennec.proto.PantheraSizeRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraSizeResponse>) {
//        reply(responseObserver, operator.size(request))
//    }
//
//    override fun deleteRow(request: db.fennec.proto.PantheraDeleteRowRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) {
//        reply(responseObserver, operator.deleteRow(request))
//    }
//
//    override fun deleteTable(request: db.fennec.proto.PantheraDeleteTableRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraOperationStatusResponse>) {
//        reply(responseObserver, operator.deleteTable(request))
//    }
//
//    override fun list(request: db.fennec.proto.PantheraListTablesRequest, responseObserver: StreamObserver<db.fennec.proto.PantheraListTablesResponse>) {
//        reply(responseObserver, operator.list(request))
//    }
}