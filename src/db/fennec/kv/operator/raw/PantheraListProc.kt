package db.fennec.kv.operator.raw

import db.fennec.error.Status

class PantheraListProc {

    fun list(request: db.fennec.proto.PantheraListTablesRequest): db.fennec.proto.PantheraListTablesResponse {
        val response = db.fennec.proto.PantheraListTablesResponse.newBuilder()
//                .addAllTable(CORE.listTableNames())
//                .setStatusCode(Status.OK.getCode())
//                .setStatusMsg(Status.OK.getDescriptor())
                .build()

//        responseObserver.onNext(response)
//        responseObserver.onCompleted()
        return response
    }
}