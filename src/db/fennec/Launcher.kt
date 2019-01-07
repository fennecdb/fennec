package db.fennec

import com.google.common.flogger.FluentLogger
import com.google.common.flogger.LoggerConfig
import db.fennec.Launcher.Companion.logger
import db.fennec.api.grpc.server.FennecGrpcServer
import db.fennec.core.ArgumentParser
import db.fennec.core.GlobalConstants
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

    FennecGrpcServer(GlobalConstants.DEFAULT_GRPC_PORT, GlobalConstants.DEFAULT_REST_PORT).run()

}