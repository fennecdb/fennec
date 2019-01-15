package db.fennec.api.web.grafana.requests

import com.google.common.flogger.FluentLogger
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import db.fennec.common.LogDefinition.Companion.config
import db.fennec.api.web.grafana.requests.GrfQueryRequest.Companion.toTimestamp
import java.io.InvalidObjectException
import java.text.SimpleDateFormat
import java.util.*

data class GrfQueryRequest(
        val timezone: String,
        val panelId: Int,
        val dashboardId: Int,
        val range: GrafanaRangeObject,
        val rangeRaw: GrafanaRawRange,
        val interval: String,
        val intervalMs: Long,
        val targets: Array<GrafanaTarget>,
        val maxDataPoints: Long,
        val scopedVars: GrafanaScopedVarsObject
) {
    companion object {
        @JvmStatic
        private val log = FluentLogger.forEnclosingClass().config()

        @Throws(InvalidObjectException::class)
        fun parse(json: String): GrfQueryRequest {
            try {
                return Gson().fromJson(json, GrfQueryRequest::class.java)
            } catch (e: JsonSyntaxException) {
                log.atWarning().withCause(e).log("Failed to parse grafana query request")
            }
            throw InvalidObjectException("Could not parse grafana query request")
        }

        fun toTimestamp(dateString: String?): Long {
            //ex: 2018-12-27T04:34:40.487Z
            if (dateString.isNullOrBlank()) {
                return 0
            }
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            parser.calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Luxembourg"))
            return parser.parse(dateString).time
        }
    }
}

data class GrafanaRangeObject(
        val from: String?,
        val to: String?,
        val raw: GrafanaRawRange
) {
    fun fromAsTimestamp(): Long {
        return toTimestamp(from)
    }

    fun toAsTimestamp(): Long {
        return toTimestamp(to)
    }
}

data class GrafanaRawRange(
        val from: String,
        val to: String
)

data class GrafanaTarget(
        val target: String,
        val refId: String,
        val type: String
) {

    fun targetAsComponents(): GrafanaTargetComponents? {
        if (target.isNotBlank()) {
            val split = target.split(":")
            if (split.size >= 5) {
                return GrafanaTargetComponents(split[1], split[2], split[3], split[4])
            }
        }
        return null
    }
}

data class GrafanaTargetComponents(
        val ns: String,
        val field: String,
        val transformation: String,
        val alias: String
)

data class GrafanaScopedVarsObject(
        @SerializedName("__interval") val interval: GrafanaRawInterval,
        @SerializedName("__interval_ms") val intervalMs: GrafanaInterval
)

data class GrafanaRawInterval(
        val text: String,
        val value: String
)

data class GrafanaInterval(
        val text: Long,
        val value: Long
)