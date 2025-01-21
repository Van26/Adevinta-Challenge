package cmm.apps.adevinta.domain.user.repository

import cmm.apps.adevinta.domain.user.model.User

interface UserRepository {
    suspend fun getUserList(toPosition: Int): List<User>
}