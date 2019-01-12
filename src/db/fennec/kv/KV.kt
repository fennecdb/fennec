package db.fennec.kv

import db.fennec.api.grpc.client.error.FennecException
import db.fennec.error.FennecExternalException
import db.fennec.fql.Key
import java.io.Closeable

interface KV : Closeable {

    fun open()
    fun put(key: Key, value: ByteArray)
    fun putAll(keyVals: Map<Key, ByteArray>)
    fun get(key: Key): ByteArray?
    fun getAll(keys: Set<Key>): Map<Key, ByteArray>
    fun remove(vararg keys: Key)
    @Throws(FennecExternalException::class) fun remove(ns: String)
    override fun close()
    fun reset()
    fun isOpen(): Boolean

    fun keys(ns: String): Iterable<String>
    fun list(): Iterable<String>

}