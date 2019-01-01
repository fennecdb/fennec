package db.fennec

import com.google.common.flogger.FluentLogger
import com.google.common.flogger.LoggerConfig
import com.google.common.util.concurrent.MoreExecutors
import com.google.common.util.concurrent.ThreadFactoryBuilder
import db.fennec.Launcher.Companion.logger
import db.fennec.core.ArgumentParser
import db.fennec.core.ArgumentParser.Companion.OPTION_PORT
import db.fennec.core.GlobalConstants
import db.fennec.remote.operation.PantheraOperation
import db.fennec.fql.FData
import db.fennec.timeseries.driver.FennecRawDriver
import io.grpc.ServerBuilder
import org.apache.commons.cli.CommandLine
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import java.util.logging.Level

class Launcher(args: Array<String>) : Runnable {

    private var clArgs: CommandLine? = null

    init {
        this.clArgs = ArgumentParser.parse(args)
    }

    override fun run() {
//        SESSION.openSession()
//        CORE.loadTableNames()
//
//        val threadFactory = ThreadFactoryBuilder()
//                .setNameFormat("fennec-worker-%d")
//                .build()
//
//        val executors = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100, threadFactory))
//
//        var usedPort: Int = GlobalConstants.PORT
//        if (clArgs != null && clArgs!!.hasOption(OPTION_PORT)) {
//            usedPort = clArgs?.getOptionValue(OPTION_PORT)!!.toInt()
//        }
//
//        println("$APP_NAME v${GlobalConstants.VERSION} starting... (Host: 'localhost', Port: $usedPort)")
//
////        MaintenanceWorker()
//        try {
//            val server = ServerBuilder
//                    .forPort(usedPort)
//                    .executor(executors)
////                    .addService(KVRemoteProcedureProvider())
//                    .build()
//            server.start()
//
//            Runtime.getRuntime().addShutdownHook(object : Thread() {
//                override fun run() {
//                    println("$APP_NAME shutting down...")
////                    SESSION.closeSession()
//                    println("$APP_NAME shutdown. See you next time :)")
//                }
//            })
//
//            server.awaitTermination()
////            executors.shutdown()
//        } catch (e: Exception) {
//            System.err.println("$APP_NAME encountered a critical error and will shutdown itself immediately.\nInformation: ''")
//        }
    }

    companion object {
        @JvmStatic val logger = FluentLogger.forEnclosingClass()

        private val FIFO_GUARANTEE = false
        // only 1 thread can use the session, and no fifo guaranteed (faster)
        @JvmStatic val SESSION_SEMA = Semaphore(1, FIFO_GUARANTEE)
        @JvmStatic val OPERATIONS = ConcurrentLinkedQueue<PantheraOperation>()
//        @JvmStatic internal var SESSION: WiredTigerSession = WiredTigerSession()
        @JvmStatic val APP_NAME = "Fennec"
        @JvmStatic val VERSION = "v0.1"
    }
}

fun main(args: Array<String>) {
    LoggerConfig.of(logger).level = Level.INFO

    val propName = "java.library.path"
    logger.atInfo().log("$propName=${System.getProperty(propName)}")
    System.loadLibrary("wiredtiger-3.1.0")
//    Fennec(args).run()

//    Launcher.SESSION.openSession()

//    val ns = FennecRawDriver.FNamespace("test", 4, TimeUnit.SECONDS)
    val fe = FennecRawDriver()

    val data = ArrayList<FData>()
    data.add(FData(3.0, 1544912246000))

    fe.insert(data, "def", "test")

    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            println("${Launcher.APP_NAME} shutting down...")
//            Launcher.SESSION.closeSession()
            println("${Launcher.APP_NAME} shutdown. See you next time :)")
        }
    })

}