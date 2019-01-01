package db.fennec.kv.wiredtiger

import org.junit.Test

class WiredTigerTest {

    @Test
    @Throws(Exception::class)
    fun testCreateSession() {
        WiredTiger.loadLibrary()
        WiredTiger.createSession()
    }

}
