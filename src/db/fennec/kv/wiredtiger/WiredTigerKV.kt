package db.fennec.kv.wiredtiger

import com.google.common.flogger.FluentLogger
import com.wiredtiger.db.WiredTigerException
import db.fennec.kv.KV
import db.fennec.kv.Key
import db.fennec.timeseries.driver.FennecRawDriver
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit

class WiredTigerKV(shouldDirectlyOpen: Boolean = false) : KV {

    private val session: WiredTigerSession = WiredTigerSession()
    private var isOpen = false

    init {
        if (shouldDirectlyOpen) {
            open()
        }
    }

    private fun acquire(body: () -> Unit) {
        try {
            SEMA.acquire()
            body.invoke()
        } finally {
            SEMA.release()
        }
    }

    override fun open() {
        acquire {
            session.openSession()
            isOpen = true
        }
    }

    override fun close() {
        acquire {
            session.closeSession()
            isOpen = false
        }
    }

    override fun reset() {
        acquire {
            session.closeSession()
            session.openSession()
        }
    }

    override fun isOpen(): Boolean {
        return isOpen
    }

    override fun put(key: Key, value: ByteArray) {
        acquire {
            val cursor = session.openCursor(key.ns)
            cursor.overwrite(key.field, value)
        }
    }

    override fun putAll(keyVals: Map<Key, ByteArray>) {
        acquire {
            for (keyValue in keyVals) {
                val cursor = session.openCursor(keyValue.key.ns)
                cursor.overwrite(keyValue.key.field, keyValue.value)
            }
        }
    }

    override fun get(key: Key): ByteArray? {
        var payload: ByteArray? = null
        acquire {
            try {
                val cursor = session.openCursor(key.ns)
                payload = cursor.get(key.field)
            } catch (wte: WiredTigerException) {
                log.atWarning().log("Failed getting '$key' (R:$wte)")
            }
        }
        return payload
    }

    override fun getAll(keys: Set<Key>): Map<Key, ByteArray> {
        val data = HashMap<Key, ByteArray>()
        acquire {
            for (key in keys) {
                val cursor = session.openCursor(key.ns)
                val payload = cursor.get(key.field)
                if (payload.isNotEmpty()) {
                    data[key] = payload
                }
            }
        }
        return data
    }

    override fun remove(vararg keys: Key) {
        acquire {
            for (key in keys) {
                val cursor = session.openCursor(key.ns)
                cursor.delete(key.field)
            }
        }
    }

    override fun remove(ns: String) {
        acquire {
            session.deleteTable(ns)
        }
    }

    // only for gc
    override fun keys(ns: String): Iterable<String> {
        var result: Iterable<String> = listOf()
        acquire {
            // TODO make acquire with pause to give up sema
            val cursor = session.openCursor(ns)
            result = cursor.keys()
        }
        return result
    }

    override fun list(): Iterable<String> {
        val result = ArrayList<String>()
        acquire {
            val tables = session.list()["table"]
            if (tables.isNotEmpty()) {
                result.addAll(tables)
            }
        }
        return result
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

        private val FIFO_GUARANTEE = false
        // only 1 thread can use the session, and no fifo guaranteed (faster)
        @JvmStatic val SEMA = Semaphore(1, FIFO_GUARANTEE)
    }
}

fun main(args: Array<String>) {
    val kv = WiredTigerKV()
    kv.open()
    kv.put(Key("test", "a"), "someString".toByteArray())
    val bytes = kv.get(Key("test", "a"))

    println("pay:${String(bytes!!)}")
    kv.close()
}