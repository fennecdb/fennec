package db.fennec.kv.operator.raw

import com.wiredtiger.db.WiredTigerException
import db.fennec.Launcher.Companion.SESSION_SEMA
import db.fennec.core.GlobalConstants.Companion.PTH_ERR_DEL_TABLE_FAILED
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerSession

class PantheraDeleteTableProc {

    fun deleteTable(session: WiredTigerSession, request: db.fennec.proto.PantheraDeleteTableRequest): db.fennec.proto.PantheraOperationStatusResponse {
        val table = request.table

        var successful = true
        var message = ""
        try {
            println("before sema")
            SESSION_SEMA.acquire()
            session.deleteTable(table)
        } catch (wte: WiredTigerException) {
//            message = commandFailMsg(PTH_ERR_DEL_TABLE_FAILED, "delete_table", wte)
            System.err.println(message)
            successful = false
        } finally {
            SESSION_SEMA.release()
            println("after sema")
        }

        val response = db.fennec.proto.PantheraOperationStatusResponse.newBuilder()
                .setSuccessful(successful)
                .setMessage(message)
                .build()

//        responseObserver.onNext(response)
//        responseObserver.onCompleted()
        return response
    }
}