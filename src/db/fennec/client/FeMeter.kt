package db.fennec.client

import com.codahale.metrics.Meter
import com.google.common.collect.ImmutableList
import com.google.common.flogger.FluentLogger
import db.fennec.api.grpc.client.FennecClient
import db.fennec.api.grpc.client.error.FennecException
import db.fennec.fql.FData

import java.time.Instant

class FeMeter(val field: String, val ns: String) {
    private val meter: Meter
    private var client: FennecClient? = null

    init {
        this.meter = Meter()
    }

    @JvmOverloads
    fun mark(n: Double? = 1.0) {
        meter.mark(n!!.toLong())
        flush(n)
    }

    private fun flush(n: Double) {
        val now = Instant.now().toEpochMilli()
        try {
            if (client == null) {
                client = FeMetrics.createClient()
            }

            client!!.insert(
                    ImmutableList.of(FData(n, now)),
                    String.format("%s_raw", field), ns
            )
            client!!.insert(
                    ImmutableList.of(FData(meter.count * 1.0, now)),
                    String.format("%s_count", field), ns
            )
            client!!.insert(
                    ImmutableList.of(FData(meter.oneMinuteRate, now)),
                    String.format("%s_m1", field), ns
            )
            client!!.insert(
                    ImmutableList.of(FData(meter.fiveMinuteRate, now)),
                    String.format("%s_m5", field), ns
            )
            client!!.insert(
                    ImmutableList.of(FData(meter.fifteenMinuteRate, now)),
                    String.format("%s_m15", field), ns
            )
        } catch (e: FennecException) {
            log.atWarning().withCause(e).log(String.format("Failed to flush metric %s", field))
            client = null
        }
    }

    companion object {

        private val log = FluentLogger.forEnclosingClass()
    }
}