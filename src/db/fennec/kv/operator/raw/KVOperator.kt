package db.fennec.kv.operator.raw

interface KVOperator {

    fun get(request: db.fennec.proto.PantheraGetRequest): db.fennec.proto.PantheraGetResponse
    fun getMulti(request: db.fennec.proto.PantheraGetMultiRequest): db.fennec.proto.PantheraGetMultiResponse
    fun getKeys(request: db.fennec.proto.PantheraGetKeysRequest): db.fennec.proto.PantheraGetKeysResponse
    fun getKeySlice(request: db.fennec.proto.PantheraGetKeySliceRequest): db.fennec.proto.PantheraGetKeysResponse
    fun insert(request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse
    fun overwrite(request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse
    fun size(request: db.fennec.proto.PantheraSizeRequest): db.fennec.proto.PantheraSizeResponse
    fun deleteRow(request: db.fennec.proto.PantheraDeleteRowRequest): db.fennec.proto.PantheraOperationStatusResponse
    fun deleteTable(request: db.fennec.proto.PantheraDeleteTableRequest): db.fennec.proto.PantheraOperationStatusResponse
    fun list(request: db.fennec.proto.PantheraListTablesRequest): db.fennec.proto.PantheraListTablesResponse

}