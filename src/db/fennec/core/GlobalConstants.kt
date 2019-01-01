package db.fennec.core

class GlobalConstants {

    companion object {
        val VERSION = "0.3"
        val HOST = "localhost"
        val DEFAULT_GRPC_PORT = 64733
        val DEFAULT_REST_PORT = 64633

        private val ERR_PRE = "PTH_ERR_"
        val PTH_ERR_GET_FAILED = "${ERR_PRE}100"
        val PTH_ERR_INSERT_FAILED = "${ERR_PRE}101"
        val PTH_ERR_OVERWRITE_FAILED = "${ERR_PRE}102"
        val PTH_ERR_DEL_TABLE_FAILED = "${ERR_PRE}103"
        val PTH_ERR_DEL_ROW_FAILED = "${ERR_PRE}104"
        val PTH_ERR_SIZE_FAILED = "${ERR_PRE}105"
        val PTH_ERR_GET_KEYS_FAILED = "${ERR_PRE}106"
        val PTH_ERR_LIST_FAILED = "${ERR_PRE}107"
    }

}