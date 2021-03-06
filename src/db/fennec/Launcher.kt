package db.fennec

import db.fennec.api.grpc.server.FennecGrpcServer
import db.fennec.common.LogDefinition
import db.fennec.core.ArgumentParser
import db.fennec.core.GlobalConstants
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
        @JvmStatic val VERSION = "v0.1"
    }
}

fun main(args: Array<String>) {
    LogDefinition.BASE_LOG_LEVEL = Level.WARNING

    var grpcPort = GlobalConstants.DEFAULT_GRPC_PORT
    var restPort = GlobalConstants.DEFAULT_REST_PORT
    if (args.isNotEmpty() && args[0] == "local") {
        grpcPort = GlobalConstants.DEV_GRPC_PORT
        restPort = GlobalConstants.DEV_REST_PORT
    }

    FennecGrpcServer(grpcPort, restPort).run()
}