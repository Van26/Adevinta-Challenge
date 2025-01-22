package cmm.apps.adevinta.view.viewmodel.userList

import cmm.apps.adevinta.domain.result.AdevintaResult
import cmm.apps.adevinta.domain.user.GetSavedUserUseCase
import cmm.apps.adevinta.view.screens.userlist.UserListViewModel
import cmm.apps.adevinta.view.viewmodel.mock.UserViewMock
import cmm.apps.adevinta.view.viewmodel.mock.UserViewMock.provideUser
import cmm.apps.adevinta.view.viewmodel.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `given a successful usecase when load method is called usecase executed and UI state containing users is emitted`() = runTest {
        val userName = "User"

        val useCase = mockk<GetSavedUserUseCase>(relaxed = true)
        coEvery { useCase(10) } returns AdevintaResult.success(UserViewMock.provideUserList(listOf(userName)))

        val sut = UserListViewModel(useCase)
        sut.loadMoreUsers()

        val uiState = sut.uiState.value
        Assert.assertEquals("$userName ${provideUser(userName).lastName}", uiState.userList[0].mainText)
    }
}