package db.fennec.fql

import db.fennec.proto.FDataBucketProto
import db.fennec.proto.FDataProto

data class FData(val value: Double, val timestamp: Long) {
    fun toProto(): FDataProto {
        return FDataProto.newBuilder()
                .setValue(value)
                .setTimestamp(timestamp)
                .build()
    }

    companion object {
        fun fromProto(proto: FDataProto): FData {
            return FData(proto.value, proto.timestamp)
        }
    }
}

data class FDataBucket(val entries: Iterable<FData>) {
    fun toProto(): FDataBucketProto {
        val builder = FDataBucketProto.newBuilder()
        for (e in entries) {
            builder.addData(e.toProto())
        }
        return builder.build()
    }

    companion object {
        fun fromProto(proto: FDataBucketProto): FDataBucket {
            val result = ArrayList<FData>()
            for (e in proto.dataList) {
                val fData = FData.fromProto(e)
                result.add(fData)
            }
            return FDataBucket(result)
        }

        fun toProto(protoData: Iterable<FDataProto>): FDataBucketProto {
            val builder = FDataBucketProto.newBuilder()
            for (e in protoData) {
                builder.addData(e)
            }
            return builder.build()
        }
    }
}