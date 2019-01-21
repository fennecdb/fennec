package db.fennec.api.web.rest

import com.codahale.metrics.Meter
import com.codahale.metrics.Timer
import db.fennec.api.web.View
import db.fennec.driver.DriverMetric
import db.fennec.driver.FennecDriver
import io.vertx.ext.web.RoutingContext
import j2html.TagCreator.*
import java.time.Instant
import java.util.EnumMap
import j2html.TagCreator.attrs
import j2html.tags.DomContent
import java.lang.Appendable
import kotlin.math.roundToInt

class DashboardView(val driver: FennecDriver) : View {

    private val uptime = Instant.now()

    private data class DashEntry(val label: String, val reqMeter: Meter?, val timer: Timer?) : DomContent() {
        override fun renderModel(writer: Appendable?, model: Any?) {
            timer!!
            with(timer.snapshot) {
                writer?.append(tr(
                        td(label),
                        td("${roundToDecimals(reqMeter!!.oneMinuteRate, 4)}"),
                        td("${min.nanoToMs().roundToInt()}"),
                        td("${max.nanoToMs().roundToInt()}"),
                        td("${mean.fNanoToMs().roundToInt()}"),
                        td("${median.fNanoToMs().roundToInt()}"),
                        td("${stdDev.fNanoToMs().roundToInt()}")
                ).render())
            }
        }

        fun Long.nanoToMs(): Double {
            return this / 1000000.0
        }

        fun Double.fNanoToMs(): Double {
            return this / 1000000.0
        }
    }

    private fun generateEntries(): Array<DashEntry> {
        val meters = driver.meters()
        val timers = driver.timers()
        val entries = arrayListOf<DashEntry>()
        entries.add(DashEntry("insert", meters[DriverMetric.INSERT_REQ], timers[DriverMetric.INSERT_TIME]))
        entries.add(DashEntry("query", meters[DriverMetric.QUERY_REQ], timers[DriverMetric.QUERY_TIME]))
        return entries.toTypedArray()
    }

    override fun render(routingContext: RoutingContext): String {
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
                                table(
                                    tr(
                                            td("Operation"),
                                            td("M1"),
                                            td("Min (ms)"),
                                            td("Max (ms)"),
                                            td("Mean (ms)"),
                                            td("Median (ms)"),
                                            td("StdDev (ms)")
                                    ),
                                    *generateEntries()
                                ).withClass("metric")
//                                ul(
//                                        li().withText("- query: ${meters.meter(DriverMetric.QUERY_REQ)} req/s"),
//                                        li().withText("- insert: ${meters.meter(DriverMetric.INSERT_REQ)} req/s (${timers.time(DriverMetric.INSERT_TIME)})"),
//                                        li().withText("- upsert: ${meters.meter(DriverMetric.UPSERT_REQ)} req/s"),
//                                        li().withText("- remove: ${meters.meter(DriverMetric.REMOVE_REQ)} req/s"),
//                                        li().withText("- remove NS: ${meters.meter(DriverMetric.REMOVE_NS_REQ)} req/s")
//                                )
                        ).withClasses("block", "block-yel")
                )
        ).render()
    }

    companion object {
        private fun roundToDecimals(number: Double, numDecimalPlaces: Int): Double {
            val factor = Math.pow(10.0, numDecimalPlaces.toDouble())
            return Math.round(number * factor) / factor
        }


    }


}