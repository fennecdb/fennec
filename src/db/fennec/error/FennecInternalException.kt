package db.fennec.error

class FennecInternalException(status: Status, message: String, throwable: Throwable? = null)
    : FennecServerException(status, message, throwable)