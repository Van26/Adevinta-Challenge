package cmm.apps.adevinta.datasource_remote.user

import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.datasource_remote.api.AdevintaUserApi
import cmm.apps.adevinta.datasource_remote.api.ExceptionHandler.manageApiException
import cmm.apps.adevinta.datasource_remote.user.mapper.toUserDataModel

class UserRemoteDatasourceImpl(private val api: AdevintaUserApi) : UserDatasource {

    override suspend fun getUserList(): List<UserDataModel> {
        return try {
            api.getUserList().results.map { it.toUserDataModel() }
        } catch (e: Exception) {
            throw manageApiException(e)
        }
    }

}