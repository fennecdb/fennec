package db.fennec.kv.wiredtiger

import com.wiredtiger.db.WiredTigerException


class WiredTiger {

    companion object {

        @Throws(WiredTigerException::class)
        fun createSession(shouldOpenDirectly: Boolean = false): WiredTigerSession {
            return WiredTigerSession(shouldOpenDirectly)
        }

        fun loadLibrary() {
            System.loadLibrary("wiredtiger-3.1.0")
        }
    }

}