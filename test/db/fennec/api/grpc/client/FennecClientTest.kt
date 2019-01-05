package db.fennec.api.grpc.client;

import com.google.common.flogger.FluentLogger
import db.fennec.api.grpc.client.error.FennecException
import db.fennec.api.grpc.server.FennecGrpcServer
import db.fennec.fql.FSelection
import db.fennec.fql.InRange
import org.junit.Assert.*
import org.junit.Test
import java.time.Instant

class FennecClientTest {

    companion object {
        @JvmStatic val log = FluentLogger.forEnclosingClass()

        val GRPC_TEST_PORT = 51234
        val REST_TEST_PORT = 51235
    }

    fun setup(body: (FennecClient) -> Unit) {
        // start server
        val server = FennecGrpcServer(GRPC_TEST_PORT, REST_TEST_PORT)
        val serverThread = Thread(server)
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
            serverThread.interrupt()
        }
    }

    @Test
    @Throws(FennecException::class)
    fun testQuery() {
        setup { client ->
            val now = Instant.now().toEpochMilli()
            val result = client.query(FSelection("x", "test", InRange(now - 1000 * 60 * 5, now)).toQuery())
            log.atInfo().log("Result: $result")
            assertEquals("Should've received empty result", 0, result.data.size())
        }
    }
}