package cmm.apps.adevinta.data.user.datasource

import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.domain.result.ErrorCodes
import cmm.apps.adevinta.domain.result.AdevintaException
import cmm.apps.adevinta.domain.result.Source

interface UserDatasource {

    suspend fun getUserList(): List<UserDataModel> {
        throw AdevintaException(message = "Unsupported operation", source = Source.UNSUPPORTED, code = ErrorCodes.UNSUPPORTED_OPERATION)
    }

    suspend fun saveUser(user: UserDataModel) {
        throw AdevintaException(message = "Unsupported operation", source = Source.UNSUPPORTED, code = ErrorCodes.UNSUPPORTED_OPERATION)
    }

}