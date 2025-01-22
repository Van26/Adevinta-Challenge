package cmm.apps.adevinta.datasource_remote.api

import cmm.apps.adevinta.domain.result.ErrorCodes
import cmm.apps.adevinta.domain.result.AdevintaException
import cmm.apps.adevinta.domain.result.Source
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.time.format.DateTimeParseException

object ExceptionHandler {
    fun manageApiException(e: Exception): AdevintaException = when (e) {
        is HttpException -> AdevintaException(message = e.response()?.message().orEmpty(), source = Source.REMOTE, code = e.code())
        is DateTimeParseException -> AdevintaException(message = "Date parse error: ${e.message.orEmpty()}", source = Source.REMOTE, code = ErrorCodes.PARSE_ERROR)
        is ConnectException,
        is UnknownHostException -> AdevintaException(message = "No connection error: ${e.message.orEmpty()}", source = Source.REMOTE, code = ErrorCodes.NO_CONNECTION)
        is AdevintaException -> e
        else -> AdevintaException(message = "Unexpected error: ${e.message.orEmpty()}", source = Source.REMOTE, code = ErrorCodes.UNKNOWN_ERROR)
    }
}