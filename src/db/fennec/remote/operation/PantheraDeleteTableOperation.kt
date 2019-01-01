package db.fennec.remote.operation

import db.fennec.kv.operator.raw.KVOperator

class PantheraDeleteTableOperation(private val request: db.fennec.proto.PantheraDeleteTableRequest, private val operator: KVOperator) : PantheraOperation {

    override fun run() {
        operator.deleteTable(request)
    }

}