package db.fennec.kv.operator.raw

import com.wiredtiger.db.WiredTigerException
import db.fennec.core.GlobalConstants.Companion.PTH_ERR_DEL_ROW_FAILED
import db.fennec.Launcher.Companion.SESSION_SEMA
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerCursor
import db.fennec.kv.wiredtiger.WiredTigerSession

class PantheraDeleteRowProc {

    fun deleteRow(session: WiredTigerSession, request: db.fennec.proto.PantheraDeleteRowRequest): db.fennec.proto.PantheraOperationStatusResponse {
        val table = request.table
        val key = request.key

        var successful = true
        var message = ""

        val cursor: WiredTigerCursor?
        try {
            SESSION_SEMA.acquire()
            cursor = session.openCursor(table)
            cursor.delete(key)
        } catch (wte: WiredTigerException) {
//            message = commandFailMsg(PTH_ERR_DEL_ROW_FAILED, "delete_row", wte)
            System.err.println(message)
            successful = false
        } finally {
//            cursor?.close()
            SESSION_SEMA.release()
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