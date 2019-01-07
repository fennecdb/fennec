package db.fennec

import com.google.common.flogger.FluentLogger
import com.google.common.flogger.LoggerConfig
import db.fennec.Launcher.Companion.logger
import db.fennec.core.ArgumentParser
import db.fennec.fql.FData
import db.fennec.driver.FennecRawDriver
import org.apache.commons.cli.CommandLine
import java.util.logging.Level

class Launcher(args: Array<String>) : Runnable {

    private var clArgs: CommandLine? = null

    init {
        this.clArgs = ArgumentParser.parse(args)
    }

    override fun run() {
    }

    companion object {
        @JvmStatic val logger = FluentLogger.forEnclosingClass()

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