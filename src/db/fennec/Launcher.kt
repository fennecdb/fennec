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

    FennecGrpcServer(GlobalConstants.DEFAULT_GRPC_PORT, GlobalConstants.DEFAULT_REST_PORT).run()
}