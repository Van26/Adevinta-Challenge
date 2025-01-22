package cmm.apps.adevinta.view.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cmm.apps.adevinta.domain.result.AdevintaResult
import cmm.apps.adevinta.domain.user.GetSavedUserUseCase
import cmm.apps.adevinta.view.di.ViewDIModule
import cmm.apps.adevinta.view.screens.userdetails.UserDetailsScreenTestTags.USER_DETAILS_CONTENT
import cmm.apps.adevinta.view.screens.userlist.UserListScreenTestTags.USER_LIST_CONTENT
import cmm.apps.adevinta.view.viewmodel.mock.UserViewMock
import cmm.apps.adevinta.view.viewmodel.mock.UserViewMock.provideUser
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.robolectric.shadows.ShadowLog

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: NavHostController

    private val getUserListUseCase = mockk<GetSavedUserUseCase>(relaxed = true).also { useCase ->
        coEvery { useCase(10) } returns AdevintaResult.success(UserViewMock.provideUserList(listOf("Adrian")))
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console

        startKoin {
            allowOverride(true)
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                ViewDIModule.module,
                module {
                    factory<GetSavedUserUseCase> { getUserListUseCase }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `given fresh app, when app is open, then user list screen is shown`() {
        setNavigationFromAppLaunch()

        composeTestRule.onNodeWithTag(USER_LIST_CONTENT).assertIsDisplayed()
    }

    @Test
    fun `given user logged, when app is open, then event list screen is shown`() {
        setNavigationFromDestination(Navigation.UserDetailsScreen(provideUser("Adrian")))

        composeTestRule.onNodeWithTag(USER_DETAILS_CONTENT).assertIsDisplayed()
    }

    private fun setNavigationFromAppLaunch() {
        composeTestRule.setContent {
            KoinContext {
                navController = rememberNavController()
                AdevintaNavigationGraph(navigationController = navController)
            }
        }
    }

    private fun setNavigationFromDestination(startDestination: Navigation) {
        composeTestRule.setContent {
            KoinContext {
                navController = rememberNavController()
                AdevintaNavHost(navigationController = navController, startDestination = startDestination)
            }
        }
    }
}

