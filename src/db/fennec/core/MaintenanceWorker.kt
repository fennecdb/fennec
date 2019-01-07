package db.fennec.core

import com.google.common.flogger.FluentLogger
import db.fennec.core.Metrics.Companion.MAINT_WORKER_IT
import db.fennec.driver.FennecDriver
import java.util.Timer
import java.util.TimerTask

class MaintenanceWorker(val driver: FennecDriver) {

    private val scheduleAtFixedRate: Timer = Timer()

    init {
        println("MaintenanceWorker registered (Delay: ${MAINTENANCE_DELAY / 1000 / 60}min).")
        registerMaintenanceLoop()
    }

    fun registerMaintenanceLoop() {
        scheduleAtFixedRate.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                maintenance()
            }
        }, 0, MAINTENANCE_DELAY)
    }

    fun maintenance() {
        MAINT_WORKER_IT.mark()
        val startTime = System.nanoTime()

        // closing session will force sync flush to drive
        driver.reset()
        val estimatedTime = System.nanoTime() - startTime
//        MAINT_WORKER_TIME
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()
        @JvmStatic val MAINTENANCE_DELAY: Long = 1000L * 60 * 5
    }
}