package db.fennec.kv.operator

import com.google.protobuf.ByteString
import db.fennec.Launcher
import db.fennec.remote.operation.PantheraDeleteRowOperation
import db.fennec.remote.operation.PantheraDeleteTableOperation
import db.fennec.remote.operation.PantheraInsertOperation
import db.fennec.remote.operation.PantheraOverwriteOperation
import db.fennec.kv.operator.raw.KVOperator
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Semaphore

@Deprecated("Abandoned idea")
class PantheraMemoryOperator(val wrappedOperator: KVOperator) : KVOperator {

//    private val executor

    override fun get(request: db.fennec.proto.PantheraGetRequest): db.fennec.proto.PantheraGetResponse {
        val cacheKey = createCacheKey(request.table, request.key)
        val response: db.fennec.proto.PantheraGetResponse
        if (inCache(request.table, request.key)) {
            response = db.fennec.proto.PantheraGetResponse.newBuilder()
                    .setKey(request.key)
                    .setPayload(ByteString.copyFrom(CACHE[request.table]!![request.key]))
                    .setMessage("")
                    .build()
        } else {
            response = wrappedOperator.get(request)
            setCache(request.table, request.key, response.payload.toByteArray())
        }
        return response
    }

    override fun getMulti(request: db.fennec.proto.PantheraGetMultiRequest): db.fennec.proto.PantheraGetMultiResponse {
        val result = ArrayList<db.fennec.proto.PantheraGetResponse>()
        val missingKeys = ArrayList<String>()
        for (key in request.keysList) {
            if (inCache(request.table, key)) {
                result.add(db.fennec.proto.PantheraGetResponse.newBuilder()
                        .setKey(key)
                        .setPayload(ByteString.copyFrom(CACHE[request.table]!![key]))
                        .setMessage("")
                        .build())
            } else {
                missingKeys.add(key)
            }
        }

        val adjRequest = request.toBuilder()
                .clearKeys()
                .addAllKeys(missingKeys)
                .build()
        val interResponse = wrappedOperator.getMulti(adjRequest)
        for (r in interResponse.responsesList) {
            setCache(request.table, r.key, r.payload.toByteArray())
            result.add(r)
        }

        return db.fennec.proto.PantheraGetMultiResponse.newBuilder()
                .addAllResponses(result)
                .build()
    }

    override fun getKeys(request: db.fennec.proto.PantheraGetKeysRequest): db.fennec.proto.PantheraGetKeysResponse {
        return wrappedOperator.getKeys(request)
    }

    override fun getKeySlice(request: db.fennec.proto.PantheraGetKeySliceRequest): db.fennec.proto.PantheraGetKeysResponse {
        return wrappedOperator.getKeySlice(request)
    }

    override fun insert(request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse {
        Launcher.OPERATIONS.add(PantheraInsertOperation(request, wrappedOperator))
        return FINE_STATUS
    }

    override fun overwrite(request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse {
        Launcher.OPERATIONS.add(PantheraOverwriteOperation(request, wrappedOperator))
        for (pair in request.pairList) {
            setCache(request.table, pair.key, pair.payload.toByteArray())
        }
        return FINE_STATUS
    }

    override fun size(request: db.fennec.proto.PantheraSizeRequest): db.fennec.proto.PantheraSizeResponse {
        return wrappedOperator.size(request)
    }

    override fun deleteRow(request: db.fennec.proto.PantheraDeleteRowRequest): db.fennec.proto.PantheraOperationStatusResponse {
        Launcher.OPERATIONS.add(PantheraDeleteRowOperation(request, wrappedOperator))
        removeEntryFromCache(request.table, request.key)
        return FINE_STATUS
    }

    override fun deleteTable(request: db.fennec.proto.PantheraDeleteTableRequest): db.fennec.proto.PantheraOperationStatusResponse {
        Launcher.OPERATIONS.add(PantheraDeleteTableOperation(request, wrappedOperator))
        removeTableFromCache(request.table)
        return FINE_STATUS
    }

    override fun list(request: db.fennec.proto.PantheraListTablesRequest): db.fennec.proto.PantheraListTablesResponse {
        return wrappedOperator.list(request)
    }

    fun setCache(table: String, key: String, payload: ByteArray) {
        try {
            SEMA.acquire()
            val values: ConcurrentHashMap<String, ByteArray>
            if (CACHE.contains(table) && CACHE[table] != null) {
                values = CACHE[table]!!
            } else {
                values = ConcurrentHashMap()
            }
            values.put(key, payload)
            CACHE[table] = values
        } finally {
            SEMA.release()
        }
    }

    fun removeEntryFromCache(table: String, key: String) {
        if (inCache(table, key)) {
            CACHE[table]?.remove(key)
        }
    }

    fun removeTableFromCache(table: String) {
        CACHE.remove(table)
    }

    fun inCache(table: String, key: String): Boolean {
        val isIn: Boolean
        try {
            SEMA.acquire()
            isIn =  CACHE.contains(table) && CACHE[table] != null && CACHE[table]!!.contains(key)
        } finally {
            SEMA.release()
        }
        return isIn
    }

    companion object {
        private fun createCacheKey(table: String, key: String): Pair<String, String> {
            return Pair(table, key)
        }

        @JvmStatic private val SEMA = Semaphore(1)
        @JvmStatic private val CACHE = ConcurrentHashMap<String, ConcurrentHashMap<String, ByteArray>>()

        @JvmStatic private val FINE_STATUS = db.fennec.proto.PantheraOperationStatusResponse.newBuilder()
            .setSuccessful(true)
            .setMessage("")
            .build()

    }
}