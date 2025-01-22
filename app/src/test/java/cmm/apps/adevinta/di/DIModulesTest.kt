package cmm.apps.adevinta.di

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cmm.apps.adevinta.component.mock.UserDataMock
import cmm.apps.adevinta.data.user.mapper.toUser
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProvider

@RunWith(AndroidJUnit4::class)
class DIModulesTest {

    private lateinit var mockContext: Context

    @Before
    fun init() {
        mockContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun verifyKoinApp() {
        koinApplication {
            MockProvider.register {
                UserDataMock.provideUserDataModel().toUser()
            }
            androidContext(mockContext)
            modules(AppDIModules.modules)
            checkModules()
        }
    }

}