package db.fennec.fql

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import db.fennec.kv.Key
import db.fennec.proto.FDataEntryProto
import db.fennec.proto.FResultProto

data class FResult(val data: Multimap<Key, FData>) {

    fun toProto(): FResultProto {
        val protoResult = FResultProto.newBuilder()
        for (d in data.entries()) {
            protoResult.addData(
                    FDataEntryProto.newBuilder()
                            .setField(d.key.field)
                            .setNamespace(d.key.ns)
                            .build()
            )
        }
        return protoResult.build()
    }

    companion object {
        fun fromProto(protoResult: FResultProto): FResult {
            val result = HashMultimap.create<Key, FData>()
            for (d: FDataEntryProto in protoResult.dataList) {
                result.put(
                        Key(d.namespace, d.field),
                        FData.fromProto(d.data)
                )
            }
            return FResult(result)
        }
    }

}