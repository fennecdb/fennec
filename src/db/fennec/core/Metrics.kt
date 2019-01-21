package db.fennec.core

import com.codahale.metrics.Meter
import com.codahale.metrics.Timer

class Metrics {
    companion object {
        // driver
        val FDRIVER_QUERY_REQ = Meter()
        val FDRIVER_INSERT_REQ = Meter()
        val FDRIVER_UPSERT_REQ = Meter()
        val FDRIVER_REMOVE_REQ = Meter()
        val FDRIVER_REMOVE_NS_REQ = Meter()

        val FDRIVER_INSERT_TIME = Timer()
        val FDRIVER_QUERY_TIME = Timer()

        // worker
        val MAINT_WORKER_IT = Meter()

        // cholla
    }
}