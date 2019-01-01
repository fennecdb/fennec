package db.fennec.api.grpc.server

import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.MoreExecutors
import com.google.common.util.concurrent.ThreadFactoryBuilder
import db.fennec.api.web.FennecRestServer
import db.fennec.core.GlobalConstants
import db.fennec.core.MaintenanceWorker
import db.fennec.timeseries.driver.FennecDriver
import db.fennec.timeseries.driver.FennecRawDriver
import io.grpc.ServerBuilder
import java.lang.Exception
import java.util.concurrent.Executors

class FennecGrpcServer(val port: Int) : Runnable {

    private val threadFactory = ThreadFactoryBuilder()
            .setNameFormat("fennec-api-worker-%d")
            .build()
    private val executors = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100, threadFactory))
    private val driver: FennecDriver = FennecRawDriver(true)
    private val restServer = FennecRestServer(driver)
    private val implementation = FennecGrpcServerImpl(driver)

    init {
        restServer.launchAsync()
    }

    override fun run() {
        log.atInfo().log("Fennec v0.1 starting up...")
        registerShutdownHook()
        MaintenanceWorker(driver)
        try {
            val server = ServerBuilder
                    .forPort(port)
                    .executor(executors)
                    .addService(implementation)
                    .build()
            server.start()
            log.atInfo().log("> Listening at port '$port'")
            server.awaitTermination()
        } catch (e: Exception) {
            log.atSevere().withCause(e).log("Fennec encountered a critical error and will shutdown itself immediately...")
        }

    }

    private fun registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                log.atInfo().log("Fennec shutting down...")
                implementation.close()
                log.atInfo().log("Fennec shutdown. See you next time :)")
            }
        })
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()
    }

}

fun main(args: Array<String>) {
    FennecGrpcServer(GlobalConstants.DEFAULT_GRPC_PORT).run()
}