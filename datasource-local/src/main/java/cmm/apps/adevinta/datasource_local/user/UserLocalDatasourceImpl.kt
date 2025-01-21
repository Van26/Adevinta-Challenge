package cmm.apps.adevinta.datasource_local.user

import android.content.SharedPreferences
import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.datasource_local.database.dao.UserDao
import cmm.apps.adevinta.datasource_local.user.mapper.toUserDataModel
import cmm.apps.adevinta.datasource_local.user.mapper.toUserLocalModel
import cmm.apps.adevinta.domain.result.ErrorCodes
import cmm.apps.adevinta.domain.result.AdevintaException
import cmm.apps.adevinta.domain.result.Source

class UserLocalDatasourceImpl(private val userDao: UserDao, private val sharedPreferences: SharedPreferences) : UserDatasource {

    override suspend fun getUserList(): List<UserDataModel> {
        return userDao.getUser()?.let { userList ->
            userList.map { it.toUserDataModel() }
        } ?: throw AdevintaException(message = "Users not found", source = Source.LOCAL, code = ErrorCodes.UNKNOWN_ERROR)
    }

    override suspend fun saveUser(user: UserDataModel) {
        userDao.insertUser(user.toUserLocalModel())
    }

}