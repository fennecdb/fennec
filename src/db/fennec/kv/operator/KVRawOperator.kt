package db.fennec.kv.operator

import com.wiredtiger.db.WiredTigerException
//import db.fennec.Launcher.Companion.SESSION
import db.fennec.kv.operator.raw.*

//class KVRawOperator : KVOperator {
//
//    private val getProcedure: PantheraGetProc
//    private val getMultiProcedure: PantheraGetMultiProc
//    private val getKeysProcedure: PantheraGetKeysProc
//    private val getKeySliceProcedure: PantheraGetKeySliceProc
//    private val oversertProcedure: PantheraOversertProc
//    private val sizeProcedure: PantheraSizeProc
//    private val deleteRowProcedure: PantheraDeleteRowProc
//    private val deleteTableProcedure: PantheraDeleteTableProc
//    private val listTablesProcedure: PantheraListProc
//
//    constructor() {
//        // procedures
//        this.getProcedure = PantheraGetProc()
//        this.getMultiProcedure = PantheraGetMultiProc()
//        this.getKeysProcedure = PantheraGetKeysProc()
//        this.getKeySliceProcedure = PantheraGetKeySliceProc()
//        this.oversertProcedure = PantheraOversertProc()
//        this.sizeProcedure = PantheraSizeProc()
//        this.deleteRowProcedure = PantheraDeleteRowProc()
//        this.deleteTableProcedure = PantheraDeleteTableProc()
//        this.listTablesProcedure = PantheraListProc()
//    }
//
//    companion object {
//        fun commandFailMsg(fail: String, command: String, wte: WiredTigerException): String {
//            return "$fail: Failed to execute '$command' command. Reason: '${wte.message}'"
//        }
//
//        fun errorExit() {
//            System.err.println("> Panthera encountered an illegal state, shutting down.")
//            System.exit(1)
//        }
//    }
//
//    override fun get(request: db.fennec.proto.PantheraGetRequest): db.fennec.proto.PantheraGetResponse {
//        return getProcedure.get(SESSION, request)
//    }
//
//    override fun getMulti(request: db.fennec.proto.PantheraGetMultiRequest): db.fennec.proto.PantheraGetMultiResponse {
//        return getMultiProcedure.getMulti(SESSION, request)
//    }
//
//    override fun getKeys(request: db.fennec.proto.PantheraGetKeysRequest): db.fennec.proto.PantheraGetKeysResponse {
//        return getKeysProcedure.getKeys(SESSION, request)
//    }
//
//    override fun getKeySlice(request: db.fennec.proto.PantheraGetKeySliceRequest): db.fennec.proto.PantheraGetKeysResponse {
//        return getKeySliceProcedure.getKeySlice(SESSION, request)
//    }
//
//    override fun insert(request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse {
//        return oversertProcedure.oversert(SESSION, false, request)
//    }
//
//    override fun overwrite(request: db.fennec.proto.PantheraOversertRequest): db.fennec.proto.PantheraOperationStatusResponse {
//        return oversertProcedure.oversert(SESSION, true, request)
//    }
//
//    override fun size(request: db.fennec.proto.PantheraSizeRequest): db.fennec.proto.PantheraSizeResponse {
//        return sizeProcedure.size(SESSION, request)
//    }
//
//    override fun deleteRow(request: db.fennec.proto.PantheraDeleteRowRequest): db.fennec.proto.PantheraOperationStatusResponse {
//        return deleteRowProcedure.deleteRow(SESSION, request)
//    }
//
//    override fun deleteTable(request: db.fennec.proto.PantheraDeleteTableRequest): db.fennec.proto.PantheraOperationStatusResponse {
//        return deleteTableProcedure.deleteTable(SESSION, request)
//    }
//
//    override fun list(request: db.fennec.proto.PantheraListTablesRequest): db.fennec.proto.PantheraListTablesResponse {
//        return listTablesProcedure.list(request)
//    }
//
//}