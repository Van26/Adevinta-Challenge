package cmm.apps.adevinta.data.user

import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.data.user.mapper.toUser
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.domain.user.repository.UserRepository

class UserRepositoryImpl(private val localDs: UserDatasource, private val remoteDs: UserDatasource) : UserRepository {

    override suspend fun getUserList(toPosition: Int): List<User> {
        val userList = mutableListOf<User>()

        val localList = try {
            localDs.getUserList()
        } catch (e: Exception) {
            emptyList()
        }

        localList.forEachIndexed { position, userDataModel ->
            if (position < toPosition) {
                userList.add(userDataModel.toUser())
            } else {
                return userList
            }
        }

        val remoteList = try {
            remoteDs.getUserList()
        } catch (e: Exception) {
            emptyList()
        }

        remoteList.forEachIndexed { position, remoteUser ->
            if (!localList.contains(remoteUser)) {
                localDs.saveUser(remoteUser)
            }
            if (position < toPosition) {
                userList.add(remoteUser.toUser())
            }
        }

        return userList
    }

}