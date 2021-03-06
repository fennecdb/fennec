package db.fennec.api.grpc.server

import com.google.common.flogger.FluentLogger
import com.google.common.util.concurrent.MoreExecutors
import com.google.common.util.concurrent.ThreadFactoryBuilder
import db.fennec.api.web.FennecRestServer
import db.fennec.core.GlobalConstants
import db.fennec.core.MaintenanceWorker
import db.fennec.driver.FennecDriver
import db.fennec.driver.FennecRawDriver
import db.fennec.common.LogDefinition.Companion.config
import io.grpc.Server
import io.grpc.ServerBuilder
import java.lang.Exception
import java.util.concurrent.Executors

class FennecGrpcServer(
        val grpcPort: Int = GlobalConstants.DEFAULT_GRPC_PORT,
        val restPort: Int = GlobalConstants.DEFAULT_REST_PORT, val shouldRunWithRest: Boolean = true) : Runnable {

    private val threadFactory = ThreadFactoryBuilder()
            .setNameFormat("fennec-api-worker-%d")
            .build()
    private val executors = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100, threadFactory))
    private val driver: FennecDriver = FennecRawDriver(true)
    private var restServer: FennecRestServer? = null
    private val methods = FennecGrpcMethods(driver)
    private var grpcServer: Server? = null

    init {
        if (shouldRunWithRest) {
            restServer = FennecRestServer(driver, restPort)
            restServer?.launchAsync()
        }
    }

    override fun run() {
        log.atInfo().log("Fennec v0.1 starting up...")
        registerShutdownHook()
        MaintenanceWorker(driver)
        try {
            grpcServer = ServerBuilder
                    .forPort(grpcPort)
                    .executor(executors)
                    .addService(methods)
                    .build()
            grpcServer?.start()
            log.atInfo().log("> Listening at port '$grpcPort'")
            grpcServer?.awaitTermination()
        } catch (e: Exception) {
            log.atSevere().withCause(e).log("Fennec encountered a critical error and will shutdown itself immediately...")
        }
    }

    fun stop() {
        methods.close()
        grpcServer?.shutdownNow()
        restServer?.halt()
    }

    private fun registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                log.atInfo().log("Fennec shutting down...")
                methods.close()
                log.atInfo().log("Fennec shutdown. See you next time :)")
            }
        })
    }

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass().config()
    }

}

fun main(args: Array<String>) {
    FennecGrpcServer(GlobalConstants.DEFAULT_GRPC_PORT, GlobalConstants.DEFAULT_REST_PORT).run()
}