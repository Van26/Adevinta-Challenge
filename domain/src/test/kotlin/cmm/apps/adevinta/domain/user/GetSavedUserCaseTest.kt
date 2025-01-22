package cmm.apps.adevinta.domain.user

import cmm.apps.adevinta.domain.mock.UserDomainMock.provideUserList
import cmm.apps.adevinta.domain.result.ErrorCodes
import cmm.apps.adevinta.domain.result.AdevintaException
import cmm.apps.adevinta.domain.result.Source
import cmm.apps.adevinta.domain.user.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetSavedUserCaseTest {

    @Test
    fun `given a successful repository when users requested then users is successfully returned`() = runTest {
        val repoUser = provideUserList(listOf("Adrian"))
        val repo = mockk<UserRepository>(relaxed = true)
        coEvery { repo.getUserList(10) } returns repoUser

        val sut = GetSavedUserUseCaseImpl(repo)
        val result = sut.invoke(10)
        Assert.assertEquals(repoUser[0], result.data!![0])
    }

    @Test
    fun `given an empty repository when users requested then failure is returned`() = runTest {
        val repo = mockk<UserRepository>(relaxed = true)
        coEvery { repo.getUserList(10) } throws AdevintaException(message = "User not found", source = Source.LOCAL, code = ErrorCodes.NO_DATA)

        val sut = GetSavedUserUseCaseImpl(repo)
        val result = sut.invoke(10)
        Assert.assertTrue(result.error is AdevintaException)
    }

}