package db.fennec.kv.operator.raw

import com.google.protobuf.ByteString
import com.wiredtiger.db.WiredTigerException
import db.fennec.Launcher.Companion.SESSION_SEMA
import db.fennec.core.GlobalConstants.Companion.PTH_ERR_GET_FAILED
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerCursor
import db.fennec.kv.wiredtiger.WiredTigerSession

class PantheraGetProc {

    fun get(session: WiredTigerSession, request: db.fennec.proto.PantheraGetRequest): db.fennec.proto.PantheraGetResponse {
        val table = request.table
        val key = request.key

        var payload = ByteArray(0)
        var message = ""
        var cursor: WiredTigerCursor? = null
        try {
            SESSION_SEMA.acquire()
            cursor = session.openCursor(table)
            payload = cursor?.get(key)
        } catch (wte: WiredTigerException) {
//            message = commandFailMsg(PTH_ERR_GET_FAILED, "get", wte)
            System.err.println(message)
        } finally {
            SESSION_SEMA.release()
        }

        val response = db.fennec.proto.PantheraGetResponse.newBuilder()
                .setKey(key)
                .setPayload(ByteString.copyFrom(payload))
                .setMessage(message)
                .build()
//
//        responseObserver.onNext(response)
//        responseObserver.onCompleted()
        return response
    }
}
