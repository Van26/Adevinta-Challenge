package cmm.apps.adevinta.domain.result

data class AdevintaResult<T>(
    val data: T? = null,
    val error: AdevintaException? = null
) {
    companion object {
        fun <T> success(value: T): AdevintaResult<T> =
            AdevintaResult(value)

        fun <T> failure(exception: Throwable): AdevintaResult<T> {
            val error: AdevintaException = if(exception is AdevintaException){
                exception
            } else {
                AdevintaException(message = "Unknown error: ${exception.message}", code = ErrorCodes.UNKNOWN_ERROR, source = Source.UNSUPPORTED)
            }
            return AdevintaResult(error = error)
        }
    }

    fun onSuccess(action: (value: T) -> Unit): AdevintaResult<T> {
        if(data != null){
            action(data)
        }
        return this
    }

    fun onFailure(action: (exception: AdevintaException) -> Unit): AdevintaResult<T> {
        if(data == null && error != null){
            action(error)
        }
        return this
    }

    fun onNoConnectionError(action: () -> Unit): AdevintaResult<T> {
        if(error != null && error.code == ErrorCodes.NO_CONNECTION){
            action()
        }
        return this
    }
}