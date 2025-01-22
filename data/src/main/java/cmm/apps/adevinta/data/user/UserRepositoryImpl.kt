package cmm.apps.adevinta.data.user

import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.data.user.mapper.toUser
import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.domain.user.repository.UserRepository

class UserRepositoryImpl(private val localDs: UserDatasource, private val remoteDs: UserDatasource) : UserRepository {

    override suspend fun getUserList(toPosition: Int): List<User> {
        val userList = mutableListOf<User>()

        val localList = getUserList(true)
        localList.forEachIndexed { position, userDataModel ->
            if (position < toPosition) {
                userList.add(userDataModel.toUser())
            } else {
                return userList
            }
        }

        val remoteList = getUserList(false)
        remoteList.forEachIndexed { position, remoteUser ->
            if (!localList.contains(remoteUser)) {
                saveUser(remoteUser)
            }
            if (position < toPosition) {
                userList.add(remoteUser.toUser())
            }
        }

        return userList
    }

    private suspend fun getUserList(isFromLocal: Boolean): List<UserDataModel> = try {
        if (isFromLocal) {
            localDs.getUserList()
        } else {
            remoteDs.getUserList()
        }
    } catch (e: Exception) {
        emptyList()
    }

    private suspend fun saveUser(user: UserDataModel) = try {
        localDs.saveUser(user)
    } catch (e: Exception) {
        // Do nothing
    }

}