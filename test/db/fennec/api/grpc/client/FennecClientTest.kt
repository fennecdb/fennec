package db.fennec.api.grpc.client;

import com.google.common.flogger.FluentLogger
import db.fennec.api.grpc.client.error.FennecException
import db.fennec.api.grpc.server.FennecGrpcServer
import db.fennec.common.LogDefinition
import db.fennec.fql.FData
import db.fennec.fql.FSelection
import db.fennec.fql.InRange
import db.fennec.fql.Key
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.util.logging.Level

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

    @Before
    fun before() {
        LogDefinition.BASE_LOG_LEVEL = Level.INFO
    }

    fun setup(body: (FennecClient) -> Unit) {
        // start server
        val server = FennecGrpcServer(GRPC_TEST_PORT, REST_TEST_PORT, false)
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
            val range = findRange()
            val initialData = listOf(dOne, dTwo, dThree, dFour)
            client.insert(initialData, field, TEST_NS)
            client.insert(listOf(dFive), field, TEST_NS)

            checkWrittenData(client, field, range, initialData, listOf(dFive))
        }
    }

    @Test
    @Throws(FennecException::class)
    fun testUpsert() {
        val field = "b"
        setup { client ->
            val range = findRange()
            client.upsert(listOf(dOne, dTwo, dThree, dFour), field, TEST_NS)
            client.upsert(listOf(dFive), field, TEST_NS)

            checkWrittenData(client, field, range, listOf(dOne, dTwo, dThree, dFive), listOf(dFour))
        }
    }

    @Test
    @Throws(FennecException::class)
    fun testRemoveNamespace() {
        val field = "c"
        setup { client ->
            client.insert(listOf(dOne), field, TEST_NS)
            client.removeNamespace(TEST_NS)

            checkWrittenData(client, field, LongRange(0, Long.MAX_VALUE), listOf(), listOf())
        }
    }

    @Test
    @Throws(FennecException::class)
    fun testRemove() {
        val field = "d"
        setup { client ->
            val input = listOf(dOne, dTwo, dThree)
            val range = findRange(input)
            client.insert(input, field, TEST_NS)
            client.remove(range.first, range.last, field, TEST_NS)
            checkWrittenData(client, field, range, listOf(), input)
        }
    }

    private fun checkWrittenData(client: FennecClient, field: String, range: LongRange, wanted: List<FData>, notWanted: List<FData>) {
        val result = client.query(FSelection(field, TEST_NS, InRange(range.first, range.last)).toQuery())
        log.atInfo().log("Result:$result")

        val data = result.data.get(Key(field, TEST_NS)).toSet()
        assertEquals("Got more data than wanted", wanted.size, data.size)
        for (d in wanted) {
            assertTrue("Could not find wanted data $d", data.contains(d))
        }
        for (d in notWanted) {
            assertTrue("Date $d should not be present", !data.contains(d))
        }
    }

    private fun findRange(of: List<FData> = listOf(dOne, dTwo, dThree, dFour, dFive)): LongRange {
        var min = dOne.timestamp
        var max = dOne.timestamp
        for (d in of) {
            if (d.timestamp < min) {
                min = d.timestamp
            }
            if (d.timestamp > max) {
                max = d.timestamp
            }
        }

        log.atInfo().log("Min:$min, Max:$max")
        return LongRange(min, max)
    }



}