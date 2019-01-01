package db.fennec.kv.operator.raw

import com.wiredtiger.db.WiredTigerException
import db.fennec.Launcher.Companion.SESSION_SEMA
import db.fennec.core.GlobalConstants
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerCursor
import db.fennec.kv.wiredtiger.WiredTigerSession

open class PantheraOversertProc {

    fun oversert(session: WiredTigerSession, overwrite: Boolean,
                 request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse {
        val table = request.table

        val keyValPairs = request.pairList

        var message = ""
        var successful = true

        var command = "insert"
        var fail = GlobalConstants.PTH_ERR_INSERT_FAILED
        if (overwrite) {
            command = "overwrite"
            fail = GlobalConstants.PTH_ERR_OVERWRITE_FAILED
        }

        var cursor: WiredTigerCursor? = null

        try {
            SESSION_SEMA.acquire()
            cursor = session.openCursor(table)
            for (pair in keyValPairs) {
                if (overwrite) {
                    cursor?.overwrite(pair.key, pair.payload.toByteArray())
                } else {
                    cursor?.insert(pair.key, pair.payload.toByteArray())
                }
            }
        } catch (wte: WiredTigerException) {
//            message = commandFailMsg(fail, command, wte)
            System.err.println(message)
            successful = false
        } finally {
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