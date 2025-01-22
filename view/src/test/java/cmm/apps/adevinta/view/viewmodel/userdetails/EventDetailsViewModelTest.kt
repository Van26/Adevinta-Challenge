package cmm.apps.adevinta.view.viewmodel.userdetails

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cmm.apps.adevinta.view.screens.userdetails.UserDetailsViewModel
import cmm.apps.adevinta.view.viewmodel.mock.UserViewMock.provideUser
import cmm.apps.adevinta.view.viewmodel.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class EventDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockContext: Context
    private lateinit var sut: UserDetailsViewModel

    private val user = provideUser("Adrian")

    @Before
    fun init() {
        mockContext = ApplicationProvider.getApplicationContext()
        startKoin {
            androidContext(mockContext)
        }
        sut = UserDetailsViewModel(user)
    }

    @After
    fun shutDown() {
        stopKoin()
    }

    @Test
    fun `given a user, when show the details screen, then the user date is shown`() = runTest {
        sut = UserDetailsViewModel(user)

        val uiState = sut.uiState.value
        Assert.assertEquals(user.gender, uiState.gender)
    }
}

