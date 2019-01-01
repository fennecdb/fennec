package db.fennec.error

class FennecInternalException(status: Status, message: String, throwable: Throwable? = null)
    : FennecException(status, message, throwable)