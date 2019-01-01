package db.fennec.kv.wiredtiger

import com.wiredtiger.db.WiredTigerException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

import org.junit.Before

class WiredTigerCursorTest {

    @Before
    fun before() {
        WiredTiger.loadLibrary()
    }

    @After
    fun after() {
        WiredTigerKV(true).use {
            it.remove(TEST_TABLE)
        }
    }


    @Throws(WiredTigerException::class)
    internal fun add(key: String, value: String) {
        WiredTiger.createSession(true).use {
            val cursor = it.openCursor(TEST_TABLE)
            cursor.insert(key, value.toByteArray())
        }
    }

    @Throws(WiredTigerException::class)
    internal fun testOverwrite(key: String, value: String) {
        WiredTiger.createSession(true).use {
            val cursor = it.openCursor(TEST_TABLE)
            cursor.overwrite(key, value.toByteArray())
        }
    }

    @Throws(WiredTigerException::class)
    internal fun deleteRow(key: String) {
        WiredTiger.createSession(true).use {
            val cursor = it.openCursor(TEST_TABLE)
            cursor.delete(key)
        }
    }

    @Test
    fun testInsert() {
        val key = "a"
        val value = "b"

        deleteRow(key)
        add(key, value)

        // open new session an try to retrieve the value
        val sessionTwo = WiredTiger.createSession(true)
        sessionTwo.use {
            val result = it.openCursor(TEST_TABLE).get(key)
            val resVal = String(result)
            println("Result: $resVal")
            assertEquals("Retrieved value incorrect.", value, resVal)
        }
    }

    @Test
    fun testInsertAlreadyExist() {
        val key = "abc"
        val value = "d"

        deleteRow(key)

        add(key, value)
        var e: Exception? = null
        try {
            add(key, "f")
        } catch (wte: WiredTigerException) {
            e = wte
        }

        assertEquals("Double insert of the same key did not fail. (Key: '$key')",
                "WT_DUPLICATE_KEY: attempt to insert an existing key", e?.message)
    }

    @Test
    fun testOverwrite() {
        val key = "efg"
        val value = "h"

        deleteRow(key)

        testOverwrite(key, value)
        var e: Exception? = null
        try {
            testOverwrite(key, "f")
        } catch (wte: WiredTigerException) {
            e = wte
        }

        assertNull("Overwrite of key '$key' failed.", e)
    }

    @Test
    fun testDeleteAndSize() {
        val key = "someKey"
        deleteRow(key)

        var session: WiredTigerSession? = null
        try {
            session = WiredTiger.createSession(true)
            val cursor = session.openCursor(TEST_TABLE)

            val beforeSize = cursor.size()
            cursor.insert(key, "someVal".toByteArray())
            val betweenSize = cursor.size()
            cursor.delete(key)
            val afterSize = cursor.size()

            assertEquals("Before size & after size dont match", beforeSize, afterSize)
            assertEquals("Between size incorrect", beforeSize + 1, betweenSize)
        } finally {
            session?.closeSession()
        }
    }

    @Test
    fun testKeys() {
        val key = "checkedKey"
        deleteRow(key)

        var session: WiredTigerSession? = null
        try {
            session = WiredTiger.createSession(true)
            val cursor = session.openCursor(TEST_TABLE)

            cursor.insert(key, "someVal".toByteArray())
            val keys = cursor.keys()
            var containsCheckedKey = false
            for (k in keys) {
                if (key == k) {
                    containsCheckedKey = true
                }
            }

            assertTrue("Checked key not found", containsCheckedKey)
        } finally {
            session?.closeSession()
        }
    }

    companion object {
        private val TEST_TABLE = "fennec_wt_cursor_test"
    }
}