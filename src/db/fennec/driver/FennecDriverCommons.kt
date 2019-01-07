package db.fennec.driver

import db.fennec.fql.FData
import db.fennec.fql.FDataBucket
import java.util.concurrent.TimeUnit

class FennecDriverCommons {
    companion object {

        fun Iterable<FData>.toByteArray(): ByteArray {
            return FDataBucket(this).toProto().toByteArray()
        }

        fun timePerBucket(times: Long, timeUnit: TimeUnit): Long {
            return TimeUnit.MILLISECONDS.convert(times, timeUnit)
        }

        fun createBucket(timestamp: Long, timePerBucket: Long): Long {
            // Example: 1544912246000 -> 1544912244000
            return (timestamp * 1.0 / timePerBucket).toLong() * timePerBucket
        }

        fun createLabel(field: String, bucket: Long): String {
            // Example: x:1544912244000
            return "$field:$bucket"
        }

        fun createMetaLabel(field: String): String {
            return "$field:meta"
        }
    }
}