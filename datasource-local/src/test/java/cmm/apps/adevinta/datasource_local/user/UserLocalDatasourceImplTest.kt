package cmm.apps.adevinta.datasource_local.user

import android.content.SharedPreferences
import cmm.apps.adevinta.datasource_local.database.dao.UserDao
import cmm.apps.adevinta.datasource_local.mock.UserLocalMock.provideUser
import cmm.apps.adevinta.datasource_local.user.mapper.toUserDataModel
import cmm.apps.adevinta.datasource_local.user.model.UserLocalModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Test

class UserLocalDatasourceImplTest {
    private var fakeStorage: UserLocalModel? = provideUser()
    private var fakeSharedToken: String? = null
    private var fakeSharedRefreshToken: String? = null

    private fun provideFakeDao(): UserDao {
        val userSlot = slot<UserLocalModel>()
        val dao = mockk<UserDao>()
        coEvery { dao.getUser() } coAnswers {
            fakeStorage?.let { listOf(it) } ?: emptyList()
        }
        coEvery { dao.insertUser(capture(userSlot)) } coAnswers {
            fakeStorage = userSlot.captured
        }
        coEvery { dao.deleteUser() } coAnswers {
            fakeStorage = null
        }

        return dao
    }

    private fun provideFakeSharedPreferences() : SharedPreferences {
        val fakeSharedTokenSlot = slot<String>()
        val fakeSharedRefreshTokenSlot = slot<String>()
        val sharedPreferences = mockk<SharedPreferences>(relaxed = true)
        coEvery { sharedPreferences.getString("refresh_token", null) } coAnswers {
            fakeSharedToken
        }
        coEvery { sharedPreferences.getString("access_token", null) } coAnswers {
            fakeSharedRefreshToken
        }
        coEvery { sharedPreferences.edit().putString("access_token", capture(fakeSharedTokenSlot)).apply() } coAnswers {
            fakeSharedToken = fakeSharedTokenSlot.captured
        }
        coEvery { sharedPreferences.edit().putString("refresh_token", capture(fakeSharedRefreshTokenSlot)).apply() } coAnswers {
            fakeSharedRefreshToken = fakeSharedRefreshTokenSlot.captured
        }
        return sharedPreferences
    }

    @After
    fun shutDown() {
        fakeStorage = null
    }

    @Test
    fun `given a working dao when user list requested then user list is successfully returned`() = runTest {
        val localUserName = "Adrian"

        val dao = mockk<UserDao>(relaxed = true)
        val sharedPreferences = mockk<SharedPreferences>(relaxed = true)
        coEvery { dao.getUser() } returns listOf(provideUser(name = localUserName))

        val sut = UserLocalDatasourceImpl(dao, sharedPreferences)
        val result = sut.getUserList()

        Assert.assertEquals(localUserName, result[0].firstName)
    }

    @Test
    fun `given an empty storage when user cached then user is stored successfully`() = runTest {
        val localUserName = "Adrian"

        val sut = UserLocalDatasourceImpl(provideFakeDao(), provideFakeSharedPreferences())
        sut.saveUser(provideUser(name = localUserName).toUserDataModel())
        val result = sut.getUserList()

        Assert.assertEquals(localUserName, result[0].firstName)
    }

}