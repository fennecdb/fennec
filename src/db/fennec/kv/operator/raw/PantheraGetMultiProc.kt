package db.fennec.kv.operator.raw

import com.google.protobuf.ByteString
import com.wiredtiger.db.WiredTigerException
import db.fennec.core.GlobalConstants
import db.fennec.Launcher.Companion.SESSION_SEMA
//import db.fennec.kv.operator.KVRawOperator.Companion.commandFailMsg
import db.fennec.kv.wiredtiger.WiredTigerCursor
import db.fennec.kv.wiredtiger.WiredTigerSession
import java.util.ArrayList

class PantheraGetMultiProc {

    fun getMulti(session: WiredTigerSession, request: db.fennec.proto.PantheraGetMultiRequest): db.fennec.proto.PantheraGetMultiResponse {
        val table = request.table
        val keys = request.keysList

        val getResponses = ArrayList<db.fennec.proto.PantheraGetResponse>()
        var cursor: WiredTigerCursor? = null
        try {
            SESSION_SEMA.acquire()
            cursor = session.openCursor(table)
            var tmp: db.fennec.proto.PantheraGetResponse
            for (key in keys) {
                val result = cursor?.get(key)

                if (!result.isEmpty()) {
                    tmp = db.fennec.proto.PantheraGetResponse.newBuilder()
                            .setKey(key)
                            .setPayload(ByteString.copyFrom(result))
                            .setMessage("")
                            .build()
                    getResponses.add(tmp)
                }
            }
        } catch (wte: WiredTigerException) {
//            val message = commandFailMsg(GlobalConstants.PTH_ERR_GET_KEYS_FAILED, "get_multi", wte)
//            System.err.println(message)
        } finally {
//            cursor?.close()
            SESSION_SEMA.release()
        }
        val response = db.fennec.proto.PantheraGetMultiResponse.newBuilder()
                .addAllResponses(getResponses)
                .build()

        return response
    }
}