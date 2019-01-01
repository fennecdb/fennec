package db.fennec.core

@Deprecated("Should be phased out")
class PantheraCore {

//    companion object {
//        val SEPERATOR = ":"
//
//        private val TABLE_NAME = "fennec.core"
//
//        private val KEY_REG_TABLES = "reg_tables"
//    }
//
//    private var tableNames: HashSet<String> = HashSet()
//
//    constructor()
//
//    fun listTableNames(): Set<String> {
//        return tableNames
//    }
//
//    private fun getSession(): WiredTigerSession {
//        return Fennec.SESSION
//    }
//
//    fun flush() {
//        try {
//            val value = tableNames.joinToString(SEPERATOR).toByteArray(Charsets.UTF_8)
//
//            val table = getSession().openCursor(TABLE_NAME)
//            table.overwrite(KEY_REG_TABLES, value)
//
//        } finally {
//            getSession().closeCursor()
//        }
//    }
//
//    fun loadTableNames(): HashSet<String> {
//        val result = HashSet<String>()
//        try {
//            Fennec.SESSION_SEMA.acquire()
//
//            val table = getSession().openCursor(TABLE_NAME)
//
//            val value = table.get(KEY_REG_TABLES)
//
//            if (value.isNotEmpty()) {
//                val strVal = String(value)
//                val list = strVal.split(":")
//
//                list.filterNotTo(result) { it.isBlank() }
//            }
//
//        } finally {
//            getSession().closeCursor()
//            Fennec.SESSION_SEMA.release()
//        }
//        return result
//    }
//
//    fun logTable(tableName: String) {
//        if (tableName.isNotBlank()) {
//            tableNames.add(tableName)
//        }
//    }
//
//    fun removeTableFromLog(tableName: String) {
//        tableNames.remove(tableName)
//    }

}