package db.fennec.driver

import com.codahale.metrics.Meter
import com.codahale.metrics.Timer
import db.fennec.fql.FQuery
import db.fennec.fql.FData
import db.fennec.fql.FResult
import java.io.Closeable
import java.util.EnumMap

interface FennecDriver : Closeable {

    fun open()
    override fun close()
    fun reset()
    fun query(query: FQuery): FResult
    fun insert(data: Iterable<FData>, field: String, ns: String)
    fun upsert(data: Iterable<FData>, field: String, ns: String)
    fun remove(timerange: LongRange, field: String, ns: String): Long
    fun remove(ns: String)
    fun gc()

    /**
     * Rebalances the buckets for a given field + namespace combination
     * @param   field   Name of the field
     * @param   ns  Namespace
     */
    fun rebalance(field: String, ns: String)

    /**
     * Returns an Iterable of all stored fields for a given namespace
     * @param   ns  namespace
     * @return      Stored fields for given namespace
     */
    fun listFields(ns: String): Iterable<String>

    /**
     * Returns an Iterable of all stored namespaces
     * @return      Stored namespaces
     */
    fun listNS(): Iterable<String>

    fun meters(): EnumMap<DriverMetric, Meter>
    fun timers(): EnumMap<DriverMetric, Timer>
}