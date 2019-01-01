package db.fennec.gatekeeper

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Semaphore

class LocalGatekeeper : Gatekeeper {

    override fun <T> acquire(field: String, ns: String, body: () -> T): T {
        val key = "${ns}_$field"

        val lock = if (LOCAL_LOCKS.containsKey(key)) {
            LOCAL_LOCKS[key]!!
        } else {
            val semaphore = Semaphore(1, false)
            LOCAL_LOCKS[key] = semaphore
            semaphore
        }
        try {
            lock.acquire()
            return body.invoke()
        } finally {
            lock.release()
        }
    }

    companion object {
        @JvmStatic private val LOCAL_LOCKS = ConcurrentHashMap<String, Semaphore>()
    }
}