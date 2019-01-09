package db.fennec.api.grpc.client;

import com.google.common.flogger.FluentLogger
import db.fennec.api.grpc.client.error.FennecException
import db.fennec.api.grpc.server.FennecGrpcServer
import db.fennec.cholla.ChollaTest.Companion.TEST_NS
import db.fennec.driver.FennecRawDriverTest
import db.fennec.fql.FData
import db.fennec.fql.FSelection
import db.fennec.fql.InRange
import db.fennec.fql.Key
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test
import java.time.Instant

class FennecClientTest {

    companion object {
        @JvmStatic val log = FluentLogger.forEnclosingClass()

        val GRPC_TEST_PORT = 51234
        val REST_TEST_PORT = 51235

        @JvmStatic val TEST_NS = "jvm_client_test"

        private val dOne = FData(1.0, 1544912246000)
        private val dTwo = FData(2.0, 1544912248000)
        private val dThree = FData(3.0, 1544912251000)
        private val dFour = FData(4.0, 1544912246030)
        private val dFive = FData(5.0, 1544912246030)
    }

    fun setup(body: (FennecClient) -> Unit) {
        // start server
        val server = FennecGrpcServer(GRPC_TEST_PORT, REST_TEST_PORT)
        val serverThread = Thread(server, "fennec-client-test")
        try {
            serverThread.start()
            // test code
            val client = FennecClient("localhost", GRPC_TEST_PORT, false)
            client.connect()
            client.use {
                body.invoke(it)
            }
        } finally {
            // stop server
            server.stop()
            serverThread.stop()
        }
    }

    @Test
    @Throws(FennecException::class)
    fun testQuery() {
        setup { client ->
            val now = Instant.now().toEpochMilli()
            val result = client.query(FSelection("x", TEST_NS, InRange(now - 1000 * 60 * 5, now)).toQuery())
            log.atInfo().log("Result: $result")
            assertEquals("Should've received empty result", 0, result.data.size())
        }
    }

    @Test
    @Throws(FennecException::class)
    fun testInsert() {
        val field = "a"
        setup { client ->
            var min = dOne.timestamp
            var max = dOne.timestamp
            for (d in listOf(dOne, dTwo, dThree, dFour, dFive)) {
                if (d.timestamp < min) {
                    min = d.timestamp
                }
                if (d.timestamp > max) {
                    max = d.timestamp
                }
            }

            log.atInfo().log("Min:$min, Max:$max")
            val initialData = listOf(dOne, dTwo, dThree, dFour)
            client.insert(initialData, field, TEST_NS)
            client.insert(listOf(dFive), field, TEST_NS)

            val result = client.query(FSelection(field, TEST_NS, InRange(min, max)).toQuery())
            log.atInfo().log("Result:$result")

            val data = result.data.get(Key(field, TEST_NS)).toSet()
            for (d in initialData) {
                assertTrue("Could not find wanted data $d", data.contains(d))
            }
        }
    }

}