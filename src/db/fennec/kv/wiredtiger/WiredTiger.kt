package db.fennec.kv.wiredtiger

import com.wiredtiger.db.WiredTigerException


class WiredTiger {

    companion object {
        init {
            System.getProperties()["java.library.path"] = "/usr/local/share/java/wiredtiger-3.1.0/"
        }

        @Throws(WiredTigerException::class)
        fun createSession(shouldOpenDirectly: Boolean = false): WiredTigerSession {
            return WiredTigerSession(shouldOpenDirectly)
        }

        fun loadLibrary() {
            System.loadLibrary("wiredtiger-3.1.0")
        }
    }

}