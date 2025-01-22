package cmm.apps.adevinta.datasource_remote

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import cmm.apps.adevinta.datasource_remote.api.AdevintaUserApi
import cmm.apps.adevinta.datasource_remote.api.NetworkApiHelper
import cmm.apps.adevinta.datasource_remote.mock.MockServer
import cmm.apps.adevinta.datasource_remote.mock.json.ServerFiles
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class AdevintaApiTest {

    private lateinit var mockServer: MockServer

    @Before
    fun init() {
        mockServer = MockServer()

        startKoin {
            modules(module {
                single<Context> { mockk() }
            })
        }
    }

    @After
    fun shutDown() {
        mockServer.shutdown()
        stopKoin()
    }

    @Test
    fun `given a successful mock server when login is requested then a correct user is returned`() = runTest {
        mockServer.enqueueFile(200, ServerFiles.GET_USERS)

        val sut = NetworkApiHelper().provideApi(mockServer.start(), AdevintaUserApi::class.java)

        val user = sut.getUserList()

        Assert.assertEquals("Adrian", user.results[0].name.firstName)
    }

}