package db.fennec.common

import com.google.common.flogger.FluentLogger
import com.google.common.flogger.LoggerConfig
import java.util.logging.Level

class LogDefinition {

    companion object {
        var BASE_LOG_LEVEL = Level.INFO

        fun FluentLogger?.config(level: Level = BASE_LOG_LEVEL): FluentLogger {
            LoggerConfig.of(this!!).level = level
            return this
        }

        fun jConfig(logger: FluentLogger): FluentLogger {
            return logger.config()
        }
    }
}