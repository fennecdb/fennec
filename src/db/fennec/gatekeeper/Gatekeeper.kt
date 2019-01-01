package db.fennec.gatekeeper

interface Gatekeeper {
    fun <T> acquire(field: String, ns: String, body: () -> T): T
}