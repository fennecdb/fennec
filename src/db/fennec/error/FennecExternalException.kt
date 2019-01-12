package db.fennec.error

class FennecExternalException(status: Status, message: String, throwable: Throwable? = null)
    : FennecServerException(status, message, throwable)