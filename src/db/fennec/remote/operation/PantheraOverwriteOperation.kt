package db.fennec.remote.operation

import db.fennec.kv.operator.raw.KVOperator

class PantheraOverwriteOperation(private val request: db.fennec.proto.PantheraOversertRequest, private val operator: KVOperator) : PantheraOperation {

    override fun run() {
        operator.overwrite(request)
    }

}