package cmm.apps.adevinta.data.user

import cmm.apps.adevinta.data.mock.UserDataMock.provideUserDataModel
import cmm.apps.adevinta.data.user.datasource.UserDatasource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UserRepositoryImplTest {

    @Test
    fun `given local data when user list requested then local user list is returned`() = runTest {
        val name = "Adrian"

        val localDS = mockk<UserDatasource>(relaxed = true)
        val remoteDS = mockk<UserDatasource>(relaxed = true)
        coEvery { localDS.getUserList() } returns listOf(provideUserDataModel(name = name))

        val sut = UserRepositoryImpl(localDS, remoteDS)
        val result = sut.getUserList(10)

        Assert.assertEquals(name, result[0].firstName)
    }
}