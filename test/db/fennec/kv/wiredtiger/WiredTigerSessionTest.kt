package db.fennec.kv.wiredtiger

import com.google.common.flogger.FluentLogger
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class WiredTigerSessionTest {

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

    @Test
    fun testOpenTable() {
        val session = WiredTiger.createSession(true)
        session.use {
            it.openCursor(TEST_TABLE)
        }
    }

    @Test
    fun testOpenCursor() {
        val session = WiredTiger.createSession(true)
        session.use {
            it.openCursor(TEST_TABLE)
            it.openCursor(TEST_TABLE)
        }
    }

    @Test
    fun testDeleteTable() {
        val session = WiredTiger.createSession(true)
        session.use {
            it.openCursor(TEST_TABLE)
            it.closeCursor()
            val result = it.deleteTable(TEST_TABLE)
            assertEquals("Failed to delete table $TEST_TABLE", 0, result)
        }
    }

    companion object {
        internal const val TEST_TABLE = "fennec_wt_session_test"

        @JvmStatic private val log = FluentLogger.forEnclosingClass()
    }
}