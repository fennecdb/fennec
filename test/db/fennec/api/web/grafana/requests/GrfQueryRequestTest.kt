package db.fennec.api.web.grafana.requests

import com.google.common.flogger.FluentLogger
import org.apache.commons.io.FileUtils
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class GrfQueryRequestTest {

    @Test
    fun testParsing() {
        val json = FileUtils.readFileToString(File("./resc/testdata/grafana/query_req_simple.json"), Charsets.UTF_8)
        log.atInfo().log(json)
        val queryRequest = GrfQueryRequest.parse(json)
        log.atInfo().log(queryRequest.toString())

        val targetAsComponents = queryRequest.targets[0].targetAsComponents()!!
        assertEquals("Incorrect target components",
                GrafanaTargetComponents("test", "x", "m5", "qwrrqeereee"), targetAsComponents)
        assertEquals("From timestamp incorrect", 1545885280487, queryRequest.range.fromAsTimestamp())
        assertEquals("To timestamp incorrect", 1545906880487, queryRequest.range.toAsTimestamp())
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()
    }

}
