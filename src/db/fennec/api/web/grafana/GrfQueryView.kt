package db.fennec.api.web.grafana

import com.google.common.flogger.FluentLogger
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import db.fennec.common.LogDefinition.Companion.config
import db.fennec.api.web.FennecRestServer.Companion.respondJson
import db.fennec.api.web.View
import db.fennec.api.web.grafana.requests.GrfQueryRequest
import db.fennec.fql.FSelection
import db.fennec.fql.InRange
import db.fennec.driver.FennecDriver
import io.vertx.ext.web.RoutingContext
import java.io.InvalidObjectException
import java.util.stream.Collectors

class GrfQueryView(val driver: FennecDriver) : View {

    override fun render(routingContext: RoutingContext): String {
        routingContext.request().bodyHandler {
            val body = it.toJsonObject()
            val result = JsonArray()

            try {
                val queryRequest = GrfQueryRequest.parse(body.toString())

                val from = queryRequest.range.fromAsTimestamp()
                val to = queryRequest.range.toAsTimestamp()
                val maxPoints = queryRequest.maxDataPoints.toInt()

                for (target in queryRequest.targets) {
                    val comps = target.targetAsComponents()
                    if (comps != null) {
                        with (comps) {
                            val dataObj = JsonObject()
                            dataObj.add("target", JsonPrimitive(alias))

                            val datapointsArr = JsonArray()
                            val query = FSelection(field = field, ns = ns, condition = InRange(from, to)).toQuery()
                            val fResult = driver.query(query)

                            var data = fResult.data.entries()
                                    .stream()
                                    .map { e -> e.value }
                                    .collect(Collectors.toList())

                            // remove random elements until we have the correct amount of data points
                            if (data.size > maxPoints) {
                                data.sortByDescending { e -> e.timestamp }
                                data = data.subList(0, maxPoints)
                            }

                            data.sortBy { e -> e.timestamp }
                            for (d in data) {
                                val point = JsonArray()
                                point.add(d.value)
                                point.add(d.timestamp)
                                datapointsArr.add(point)
                            }

                            // dummy data
//                            for (i in (now - 1000 * 1000 * 600)..now step 100000) {
//                                val point = JsonArray()
//                                point.add(Random().nextInt((500 + 1) - 10) + 10)
//                                point.add(i)
//                                datapointsArr.add(point)
//                            }
                            dataObj.add("datapoints", datapointsArr)
                            result.add(dataObj)
                        }
                    }
                }
            } catch (e: InvalidObjectException) {
                log.atWarning().withCause(e).log("Failed to parse query request")
            } finally {
                respondJson(routingContext, result.toString())
            }
        }
        return ""
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass().config()
    }
}