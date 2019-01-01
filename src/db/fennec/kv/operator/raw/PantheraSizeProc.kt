package db.fennec.kv.operator.raw

import com.wiredtiger.db.WiredTigerException
import db.fennec.Launcher.Companion.SESSION_SEMA
import db.fennec.core.GlobalConstants.Companion.PTH_ERR_SIZE_FAILED
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerCursor
import db.fennec.kv.wiredtiger.WiredTigerSession

class PantheraSizeProc {

    fun size(session: WiredTigerSession, request: db.fennec.proto.PantheraSizeRequest): db.fennec.proto.PantheraSizeResponse {
        val table = request.table

        var size = 0L
        var message: String
        var cursor: WiredTigerCursor? = null
        try {
            SESSION_SEMA.acquire()
            cursor = session.openCursor(table)
            size = cursor?.size()
        } catch (wte: WiredTigerException) {
//            message = commandFailMsg(PTH_ERR_SIZE_FAILED, "size", wte)
//            System.err.println(message)
        } finally {
//            cursor?.close()
            SESSION_SEMA.release()
        }

        val response = db.fennec.proto.PantheraSizeResponse.newBuilder()
                .setSize(size)
                .build()

//        responseObserver.onNext(response)
//        responseObserver.onCompleted()
        return response
    }
}