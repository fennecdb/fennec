package db.fennec.gatekeeper

class DistributedGatekeeper : Gatekeeper {

    override fun <T> acquire(field: String, ns: String, body: () -> T): T {
        try {
            //TODO
            return body.invoke()
        } finally {
        }
    }

}