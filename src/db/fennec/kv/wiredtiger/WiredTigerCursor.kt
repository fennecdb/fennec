package db.fennec.kv.wiredtiger

import com.wiredtiger.db.Cursor
import com.wiredtiger.db.WiredTigerException
import java.io.Closeable
import java.util.ArrayList

class WiredTigerCursor(private val tableName: String, private val cursor: Cursor) : Closeable {

    fun getTableName(): String {
        return tableName
    }

    override fun close() {
        cursor.close()
    }

    @Throws(WiredTigerException::class)
    fun insert(key: String, value: ByteArray) {
        safeReset()
        cursor.reconfigure("overwrite=false")
        cursor.putKeyString(key)
        cursor.putValueByteArray(value)
        cursor.insert()
    }

    @Throws(WiredTigerException::class)
    fun overwrite(key: String, value: ByteArray) {
        safeReset()
        cursor.reconfigure("overwrite=true")
        cursor.putKeyString(key)
        cursor.putValueByteArray(value)
        cursor.insert()
    }

    @Throws(WiredTigerException::class)
    fun get(key: String): ByteArray {
        safeReset()
        cursor.putKeyString(key)
        val result = cursor.search()
        // zero on success and a non-zero error code on failure.
        if (result == 0) {
            return cursor.getValueByteArray()
        }
        return ByteArray(0)
    }

    @Throws(WiredTigerException::class)
    fun delete(key: String) {
        safeReset()
        cursor.putKeyString(key)
        cursor.remove()
    }

    @Throws(WiredTigerException::class)
    fun size(): Long {
        safeReset()

        var size: Long = 0
        while (cursor.next() == 0) {
            size++
        }
        return size
    }

    @Throws(WiredTigerException::class)
    fun keys(): List<String> {
        safeReset()

        val result = ArrayList<String>()
        while (cursor.next() == 0) {
            result.add(cursor.getKeyString())
        }
        return result
    }

    @Throws(WiredTigerException::class)
    fun keySlice(start: Long, size: Int): List<String> {
        safeReset()

        var i = 0L
        var added = 0
        val result = ArrayList<String>()
        while (cursor.next() == 0 && added < size) {
            if (i >= start) {
                result.add(cursor.getKeyString())
                added++
            }
            i++
        }
        return result
    }

    fun safeReset(): Boolean {
        var successful = false
        try {
            cursor.reset()
            successful = true
        } catch (e: NullPointerException) {
            println("Error while resetting cursor")
        }
        return successful
    }
}
