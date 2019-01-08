package db.fennec.kv.wiredtiger

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.google.common.flogger.FluentLogger
import com.wiredtiger.db.Connection
import com.wiredtiger.db.WiredTigerException
import com.wiredtiger.db.Session
import com.wiredtiger.db.Cursor
import com.wiredtiger.db.wiredtiger
import db.fennec.kv.wiredtiger.WiredTigerConstants.Companion.DB_DIR
//import org.apache.commons.io.IOUtils
import java.io.Closeable
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit
import java.io.InputStreamReader
import com.google.common.io.CharStreams
import java.io.BufferedReader
import java.util.*


class WiredTigerSession(shouldOpenSessionDirectly: Boolean = false) : Closeable {

    private var connection: Connection? = null
    private var session: Session? = null
    private var lastCursor: WiredTigerCursor? = null
    private var isSessionOpen = false

    init {
        if (shouldOpenSessionDirectly) {
            openSession()
        }
    }

    fun openSession() {
        // create, cache_size=50M, in_memory=false, log=(enabled),transaction_sync=(enabled=true,method=fsync)
        this.connection = wiredtiger.open(DB_DIR, "create, cache_size=50M, in_memory=false, log=(enabled),transaction_sync=(enabled=true,method=fsync)")
        this.session = this.connection!!.open_session("isolation=read-committed")
        isSessionOpen = true
    }

    fun closeSession() {
        closeCursor()
        closeWithCatch("session") { session?.close("") }
        closeWithCatch("connection") { connection?.close("") }
        isSessionOpen = false
    }

    private fun closeWithCatch(desc: String, closeAction: () -> Unit) {
        try {
            closeAction.invoke()
        } catch (e: Exception) {
            log.atWarning().log("Failure during closing wt $desc")
        }
    }

    override fun close() {
        closeSession()
    }

    fun closeCursor() {
        lastCursor?.close()
        lastCursor = null
    }

    // Note: Very expensive call ('wt' only feature (CLI tool))
    fun cliList(): Multimap<String, String> {
        val wasSessionOpen = isSessionOpen
        if (isSessionOpen) { // need to close session to execute wt command
            closeSession()
        }
        val result = HashMultimap.create<String, String>()
        val builder = ProcessBuilder("bash", "-c", "cd $DB_DIR; wt list")
        builder.redirectErrorStream(false)
        val process = builder.start()
        try {
            val hasFinishedOnTime = process.waitFor(2, TimeUnit.SECONDS)
            if (hasFinishedOnTime) {
                val lines = BufferedReader(InputStreamReader(process.inputStream)).lines()
                for (line in lines) {
                    val colonIndex = line.indexOf(':')
                    if (colonIndex > 0) {
                        result.put(line.substring(0, colonIndex), line.substring(colonIndex + 1))
                    }
                }
            }
        } catch (e: InterruptedException) {
            log.atWarning().withCause(e).log("Failed getting wt list")
        } finally {
            if (wasSessionOpen) {
                openSession()
            }
        }
        return result
    }

    fun beginTransaction() {
        session!!.begin_transaction("")
    }

    fun commitTransaction(): Boolean {
        var success = false
        try {
            session!!.commit_transaction("")
            success = true
        } catch (e: WiredTigerException) {
            println("Committing transaction failed, rolling back. Reason: ${e.message}")
        }
        return success
    }

    @Throws(WiredTigerException::class)
    private fun checkSession() {
        if (isSessionOpen.not()) {
            log.atSevere().log("Illegal State: Session was not initialized.")
            throw WiredTigerException("Fennec WT Session is not initialized (maybe forgot to call 'openSession'?)")
        }
    }

    @Throws(WiredTigerException::class)
    internal fun _openCursor(tableName: String, option: String? = null): Cursor {
        checkSession()
        return session?.open_cursor("table:$tableName", null, option)!!
    }

    @Throws(WiredTigerException::class)
    fun openCursor(tableName: String, option: String? = null): WiredTigerCursor {
        checkSession()
        if (lastCursor != null && tableName.equals(lastCursor?.getTableName())) {
            return lastCursor!!
        }

        var cursor: Cursor? = null
        try {
            cursor = _openCursor(tableName, option)
        } catch (wte: WiredTigerException) {
            if ("No such file or directory".equals(wte.message)) {
                // table does not exist
                cursor?.close()
                session?.create("table:$tableName", "key_format=S,value_format=u")
                cursor = _openCursor(tableName, option)
            } else {
                closeSession()
                throw wte
            }
        }

        lastCursor = WiredTigerCursor(tableName, cursor!!)
        return lastCursor!!
    }

    @Throws(WiredTigerException::class)
    fun deleteTable(tableName: String): Int {
        checkSession()
        closeCursor()

        var e: Exception? = null
        try {
            return session!!.drop("table:$tableName", "")
        } catch (wte: WiredTigerException) {
            when {
                "Device or resource busy".equals(wte.message) ->
                    System.err.println("Table '$tableName' could not be deleted. Needs to closeSession all cursors on this table first.")
                "No such file or directory".equals(wte.message) ->
                    System.err.println("Table '$tableName' could not be deleted, as it does not exist.")
                else -> {
                    closeSession()
                    throw wte
                }
            }
            e = wte
        } finally {
        }

        return -1
    }

    companion object {
        private val log = FluentLogger.forEnclosingClass()

        init {
            WiredTiger.loadLibrary()
        }
    }
}