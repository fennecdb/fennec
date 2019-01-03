package db.fennec.error

class FennecExternalExceptionException(status: Status, message: String, throwable: Throwable? = null)
    : FennecServerException(status, message, throwable)