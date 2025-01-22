package cmm.apps.adevinta.datasource_remote.user

import cmm.apps.adevinta.datasource_remote.api.AdevintaUserApi
import cmm.apps.adevinta.datasource_remote.mock.UserRemoteMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UserRemoteDatasourceImplTest {

    @Test
    fun `given user list requested when is success then user list is returned`() = runTest {
        val remoteUserName = "Adrian"

        val api = mockk<AdevintaUserApi>(relaxed = true)
        coEvery { api.getUserList() } returns UserRemoteMock.provideUser(remoteUserName)

        val sut = UserRemoteDatasourceImpl(api)
        val result = sut.getUserList()

        Assert.assertEquals(remoteUserName, result[0].firstName)
    }

}