package db.fennec.error

enum class Status(private val code: Long, private val descriptor: String) {
    OK(200, "OK"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    UNCLASSIFIED(600, "Unclassified error occurred.");

    fun getCode(): Long {
        return code
    }

    fun getDescriptor(): String {
        return descriptor
    }

    override fun toString(): String {
        return "StatusCode{$code, $descriptor}"
    }

    companion object {
        fun fromCode(code: Long): Status {
            for (statusCode in Status. values()) {
                if (code == statusCode.code) {
                    return statusCode
                }
            }
            return UNCLASSIFIED
        }
    }
}