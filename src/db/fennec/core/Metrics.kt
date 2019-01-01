package db.fennec.core

import com.codahale.metrics.Meter

class Metrics {
    companion object {
        val FDRIVER_QUERY_REQ = Meter()
        val FDRIVER_INSERT_REQ = Meter()
        val FDRIVER_UPSERT_REQ = Meter()
        val FDRIVER_REMOVE_REQ = Meter()
        val FDRIVER_REMOVE_NS_REQ = Meter()
        val MAINT_WORKER_IT = Meter()
    }
}