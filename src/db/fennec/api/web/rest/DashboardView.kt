package db.fennec.api.web.rest

import com.codahale.metrics.Meter
import db.fennec.api.web.View
import db.fennec.timeseries.driver.DriverMetric
import db.fennec.timeseries.driver.FennecDriver
import io.vertx.ext.web.RoutingContext
import j2html.TagCreator.*
import java.time.Instant
import java.util.*

class DashboardView(val driver: FennecDriver) : View {

    private val uptime = Instant.now()

    override fun render(routingContext: RoutingContext): String {
        val metrics = driver.metrics()
        return html(
                head(
                        title("Dashboard - Fennec"),
                        link().withRel("stylesheet").withHref("/style/main.css"),
                        link().withRel("stylesheet").withHref("https://fonts.googleapis.com/css?family=Noto+Sans")
                ),
                body(
                        header("Fennec Dashboard"),
                        main(attrs("#main.content"),
                                h3("Up since $uptime")
                        ).withClasses("block", "block-mar"),
                        div(
                                h3("Endpoints"),
                                ul(
                                        li().withText("- query: ${metrics.value(DriverMetric.QUERY_REQ)} req/s"),
                                        li().withText("- insert: ${metrics.value(DriverMetric.INSERT_REQ)} req/s"),
                                        li().withText("- upsert: ${metrics.value(DriverMetric.UPSERT_REQ)} req/s"),
                                        li().withText("- remove: ${metrics.value(DriverMetric.REMOVE_REQ)} req/s"),
                                        li().withText("- remove NS: ${metrics.value(DriverMetric.REMOVE_NS_REQ)} req/s")
                                )
                        ).withClasses("block", "block-yel")
                )
        ).render()
    }

    private fun EnumMap<DriverMetric, Meter>.value(dMetric: DriverMetric): String {
        return roundToDecimals(this[dMetric]!!.oneMinuteRate, 4).toString()
    }

    private fun roundToDecimals(number: Double, numDecimalPlaces: Int): Double {
        val factor = Math.pow(10.0, numDecimalPlaces.toDouble())
        return Math.round(number * factor) / factor
    }

}