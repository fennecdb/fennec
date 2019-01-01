package db.fennec.fql

class FQL

data class FSelection(val field: String, val ns: String, val condition: FQLWhereCondition) {

    fun toQuery(): FQuery {
        return FQuery(this)
    }

}

data class FQuery(val selections: Iterable<FSelection>) {
    constructor(selection: FSelection) : this(listOf(selection))
}

interface FQLWhereCondition {
    fun min(): Long

    fun max(): Long

    fun evaluate(timestamp: Long): Boolean
}

data class InRange(val min: Long, val max: Long) : FQLWhereCondition {
    override fun min(): Long {
        return min
    }

    override fun max(): Long {
        return max
    }

    override fun evaluate(timestamp: Long): Boolean {
        return timestamp in min..max
    }
}

fun main(args: Array<String>) {
    val query: FQuery = FSelection("x", "test", InRange(0L, 3L)).toQuery()
    println(query)
}