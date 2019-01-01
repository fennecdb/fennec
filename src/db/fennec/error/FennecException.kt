package db.fennec.error

import java.lang.Exception

open class FennecException(val status: Status, final override val message: String, throwable: Throwable? = null)
    : Exception("${status.getDescriptor()} - $message", throwable) {

    val structuredMsg: String = "${status.getDescriptor()} - $message"
}