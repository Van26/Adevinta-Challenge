package cmm.apps.adevinta.component.userlist

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cmm.apps.adevinta.component.mock.UserDataMock
import cmm.apps.adevinta.component.mock.MockApplication
import cmm.apps.adevinta.data.di.DataDIModule.REMOTE_DATASOURCE_INSTANCE_NAME
import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.datasource_local.database.AdevintaDatabase
import cmm.apps.adevinta.di.AppDIModules
import cmm.apps.adevinta.domain.user.GetSavedUserUseCase
import cmm.apps.adevinta.view.screens.userlist.UserListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@Config(application = MockApplication::class)
class UserListViewModelComponentTest : KoinTest {

    private lateinit var mockContext: Context
    private lateinit var mockDatabase: AdevintaDatabase
    private lateinit var remoteDatasource: UserDatasource

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun init() {
        mockContext = ApplicationProvider.getApplicationContext()
        mockDatabase = Room
            .inMemoryDatabaseBuilder(mockContext, AdevintaDatabase::class.java)
            .allowMainThreadQueries()
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()
    }

    @After
    fun shutDown() {
        mockDatabase.close()

        stopKoin()
    }

    private fun startDI() {
        startKoin {
            androidContext(mockContext)
            modules(
                AppDIModules.modules,
                module {
                    single<AdevintaDatabase> { mockDatabase }
                    factory<UserDatasource>(named(REMOTE_DATASOURCE_INSTANCE_NAME)) { remoteDatasource }
                }
            )
        }
    }

    @Test
    fun `given a successful API and an empty DB when screen is shown then UI state with users is returned`() = runTest {
        val remoteUserName = "User Name"
        remoteDatasource = mockk<UserDatasource>()
        coEvery { remoteDatasource.getUserList() } returns UserDataMock.provideUserDataModelList(listOf(remoteUserName))
        startDI()

        val useCase: GetSavedUserUseCase by inject()

        val sut = UserListViewModel(useCase)

        sut.loadMoreUsers()

        val uiState = sut.uiState.value
        Assert.assertTrue(uiState.userList[0].mainText.contains(remoteUserName))
    }
}