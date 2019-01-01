package db.fennec.kv.operator.raw

import com.wiredtiger.db.WiredTigerException
import db.fennec.Launcher.Companion.SESSION_SEMA
import db.fennec.core.GlobalConstants.Companion.PTH_ERR_GET_KEYS_FAILED
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerCursor
import db.fennec.kv.wiredtiger.WiredTigerSession

class PantheraGetKeysProc {

    fun getKeys(session: WiredTigerSession, request: db.fennec.proto.PantheraGetKeysRequest): db.fennec.proto.PantheraGetKeysResponse {
        val table = request.table

        var keys = listOf<String>()
        var cursor: WiredTigerCursor? = null
        try {
            SESSION_SEMA.acquire()
            cursor = session.openCursor(table)
//            keys = cursor?.keys()
            cursor?.keys()
        } catch (wte: WiredTigerException) {
//            val message = commandFailMsg(PTH_ERR_GET_KEYS_FAILED, "get_keys", wte)
//            System.err.println(message)
        } finally {
            SESSION_SEMA.release()
        }
        val response = db.fennec.proto.PantheraGetKeysResponse.newBuilder()
                .addAllKey(keys)
                .build()

//        try {
//            responseObserver.onNext(response)
//            responseObserver.onCompleted()
//        } catch (e: Exception) {
////            e.printStackTrace()
//        }
        return response
    }
}