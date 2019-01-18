package db.fennec.client


import com.google.common.base.Supplier
import com.google.common.base.Suppliers
import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.Uninterruptibles
import db.fennec.api.grpc.client.FennecClient
import db.fennec.api.grpc.client.error.FennecException

import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

object FeMetrics {

    private val log = FluentLogger.forEnclosingClass()

    fun createClient(): FennecClient? {
        var client: FennecClient? = null
        try {
            log.atWarning().log("Init fennec client")
            client = FennecClient("localhost", 64733)
            client.connect()
        } catch (e: FennecException) {
            log.atWarning().withCause(e).log("Failed to connect to fennec")
        }

        return client
    }

    fun createMeter(field: String, ns: String): FeMeter {
        return FeMeter(field, ns)
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {

        val meter = createMeter("test", "metric_test")
        while (true) {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS)
            meter.mark(ThreadLocalRandom.current().nextInt(0, 500 + 1) * 1.0)
        }
    }
}