package cmm.apps.adevinta.data.user

import cmm.apps.adevinta.data.mock.UserDataMock.provideUserDataModelList
import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.data.user.model.UserDataModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UserRepositoryImplTest {

    @Test
    fun `given local data when user list requested more then local user list is returned excluding equals from remote`() = runTest {
        val name = "Adrian"

        val localDS = mockk<UserDatasource>(relaxed = true)
        val remoteDS = mockk<UserDatasource>(relaxed = true)
        coEvery { localDS.getUserList() } returns provideUserDataModelList(List(10) { name })
        coEvery { remoteDS.getUserList() } returns provideUserDataModelList(List(5) { name })

        val sut = UserRepositoryImpl(localDS, remoteDS)
        val result = sut.getUserList(15)

        Assert.assertEquals(name, result[0].firstName)
        Assert.assertEquals(10, result.size)
    }

    @Test
    fun `given local data when user list requested more then local user list is returned including not equals from remote`() = runTest {
        val name = "Adrian"

        val localDS = mockk<UserDatasource>(relaxed = true)
        val remoteDS = mockk<UserDatasource>(relaxed = true)
        coEvery { localDS.getUserList() } returns provideUserDataModelList(List(10) { name })
        coEvery { remoteDS.getUserList() } returns provideUserDataModelList(listOf("Pablo"))

        val sut = UserRepositoryImpl(localDS, remoteDS)
        val result = sut.getUserList(11)

        Assert.assertEquals(name, result[0].firstName)
        Assert.assertEquals(11, result.size)
    }

    // Other test without mockk (faster in terms of execution time)
    @Test
    fun `given local data when user list requested from remote then save it in local`() = runTest {
        var userIsSaved = false
        val values = listOf("Adrian")
        val sut = UserRepositoryImpl(
            getUserDatasource(emptyList()) {
                userIsSaved = true
            },
            getUserDatasource(values)
        )
        sut.getUserList(10)

        Assert.assertTrue(userIsSaved)
    }

    @Test
    fun `given remote data when not all are equals then save just the different results`() = runTest {
        var userIsSaved = 0
        val repeatedName = "Adrian"
        val localValues = List(10) { repeatedName }
        val sut = UserRepositoryImpl(
            getUserDatasource(localValues) {
                userIsSaved++
            },
            getUserDatasource(listOf(repeatedName, "Pablo", repeatedName, "Alfonso"))
        )
        sut.getUserList(10)

        Assert.assertEquals(2, userIsSaved)
    }

    private fun getUserDatasource(nameList: List<String>, onSaved: (() -> Unit)? = null) = object: UserDatasource {

        override suspend fun getUserList(): List<UserDataModel>  {
            return provideUserDataModelList(nameList)
        }

        override suspend fun saveUser(user: UserDataModel) {
            onSaved?.invoke()
        }
    }
}