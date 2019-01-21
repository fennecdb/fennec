package db.fennec.metrics

import com.codahale.metrics.Snapshot
import java.util.concurrent.TimeUnit

class Snap(val snapshot: Snapshot, val timeUnit: TimeUnit) {

    fun min(): Long {
        return timeUnit.convert(snapshot.min, TimeUnit.NANOSECONDS)
    }

    fun max(): Long {
        return timeUnit.convert(snapshot.max, TimeUnit.NANOSECONDS)
    }

    fun mean(): Long {
        return timeUnit.convert(snapshot.mean.toLong(), TimeUnit.NANOSECONDS)
    }

    fun median(): Long {
        return timeUnit.convert(snapshot.median.toLong(), TimeUnit.NANOSECONDS)
    }

    fun stdDev(): Long {
        return timeUnit.convert(snapshot.stdDev.toLong(), TimeUnit.NANOSECONDS)
    }

    override fun toString(): String {
        return "Snap(min=${min()}, max=${max()}, mean=${mean()}, median=${median()}, stdDev=${stdDev()})"
    }
}