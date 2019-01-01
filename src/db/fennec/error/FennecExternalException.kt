package db.fennec.error

import java.lang.Exception

class FennecExternalExceptionException(status: Status, message: String, throwable: Throwable? = null)
    : FennecException(status, message, throwable)