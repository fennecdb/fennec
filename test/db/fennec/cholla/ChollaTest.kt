package db.fennec.cholla

import com.google.common.flogger.FluentLogger
import db.fennec.driver.FennecRawDriver
import db.fennec.driver.FennecRawDriverTest
import db.fennec.fql.FData
import db.fennec.fql.Key
import db.fennec.kv.wiredtiger.WiredTigerKV
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Test

class ChollaTest {

    @After
    fun after() {
        FennecRawDriver(true).use { driver ->
            driver.remove(TEST_NS)
        }
    }

    @Test
    fun testClean() {
        val field = "xyz"
        val fineField = "def"
        val data = FData(3.0, 1544912246000)
        val driver = FennecRawDriver(true)
        driver.use {
            driver.insert(listOf(data), field = field, ns = TEST_NS)
            driver.insert(listOf(FData(3.0, 1544912246000)), fineField, TEST_NS)
            driver.remove(LongRange(data.timestamp, data.timestamp), field, TEST_NS)
        }

        WiredTigerKV(true).use { kv ->
            val cholla = Cholla(kv)
            val status = cholla.runWithStatus(nsWhitelist = setOf(TEST_NS))
            log.atInfo().log("Status:$status")

            val wantedRemovedKey = Key(field = field, ns =  TEST_NS)
            assertTrue("Key $wantedRemovedKey was not removed", status.removed.contains(wantedRemovedKey))
            val wantedKeptKey = Key(field = fineField, ns = TEST_NS)
            assertTrue("Key $wantedKeptKey was kept", !status.removed.contains(wantedKeptKey))
        }
    }

    companion object {
        @JvmStatic val log = FluentLogger.forEnclosingClass()

        val TEST_NS = "cholla_test"
    }
}