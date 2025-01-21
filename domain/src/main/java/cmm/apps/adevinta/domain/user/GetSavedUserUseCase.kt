package cmm.apps.adevinta.domain.user

import cmm.apps.adevinta.domain.result.AdevintaResult
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.domain.user.repository.UserRepository

interface GetSavedUserUseCase {
    suspend operator fun invoke(toPosition: Int): AdevintaResult<List<User>>
}

class GetSavedUserUseCaseImpl(private val repo: UserRepository) : GetSavedUserUseCase {
    override suspend fun invoke(toPosition: Int): AdevintaResult<List<User>> {
        try {
            val result = repo.getUserList(toPosition)
            return AdevintaResult.success(result)
        } catch (e: Exception) {
            return AdevintaResult.failure(e)
        }
    }
}