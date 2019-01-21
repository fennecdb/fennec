package db.fennec.benchmark

import com.codahale.metrics.Timer
import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.RateLimiter
import com.google.common.util.concurrent.Uninterruptibles
import db.fennec.api.grpc.client.FennecClient
import db.fennec.core.GlobalConstants
import db.fennec.common.LogDefinition.Companion.config
import db.fennec.fql.FData
import java.lang.Exception
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ThreadLocalRandom


class HighInLowOutBenchmark(val field: String, val ns: String) : Runnable {

    private val client = FennecClient("localhost", GlobalConstants.DEV_GRPC_PORT)


    override fun run() {
        client.connect()
        val rateLimiter = RateLimiter.create(8.0)
        val timer = Timer()

        val every = 1

        client.removeNamespace(ns)
        var numIteration: Long = 0
        client.use { client ->
            while (true) {
                rateLimiter.acquire()

                timer.time {

                        val randomValue = ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)
                        val randomTimestamp = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE)
                        val data = FData(randomValue, randomTimestamp)
                        client.insert(listOf(data), field, ns)

                }
                numIteration++
                if ((numIteration % every) == 0L) {
                }

                with (timer.snapshot) {
                    log.atWarning().log("i=$numIteration, min=$min, max=$max, avg=$mean, median=$median")
                }

            }
        }
    }

    companion object {
        private val log = FluentLogger.forEnclosingClass().config()

    }
}

fun main(args: Array<String>) {


    val numThreads = 4
    val executor = Executors.newFixedThreadPool(numThreads)
    val futures = ArrayList<Future<*>>()
    for (i in 0..numThreads) {
        futures.add(executor.submit {
            HighInLowOutBenchmark("bench_$i", "benchmark_$i").run()
        })
    }

    for (future in futures) {
        try {
            Uninterruptibles.getUninterruptibly(future)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    System.exit(0)
}