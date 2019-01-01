package db.fennec.remote.operation

import db.fennec.kv.operator.raw.KVOperator

class PantheraDeleteRowOperation(private val request: db.fennec.proto.PantheraDeleteRowRequest, private val operator: KVOperator) : PantheraOperation {

    override fun run() {
        operator.deleteRow(request)
    }

}