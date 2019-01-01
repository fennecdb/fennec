package db.fennec.common

import com.google.common.flogger.FluentLogger
import java.util.concurrent.TimeUnit

class TimeUnits {

    companion object {
        @JvmStatic private val log = FluentLogger.forEnclosingClass()

        fun shortName(timeUnit: TimeUnit): String {
            return when (timeUnit) {
                TimeUnit.NANOSECONDS -> "a"
                TimeUnit.MICROSECONDS -> "b"
                TimeUnit.MILLISECONDS -> "c"
                TimeUnit.SECONDS -> "d"
                TimeUnit.MINUTES -> "e"
                TimeUnit.HOURS -> "f"
                TimeUnit.DAYS -> "g"
            }
        }

    }
}