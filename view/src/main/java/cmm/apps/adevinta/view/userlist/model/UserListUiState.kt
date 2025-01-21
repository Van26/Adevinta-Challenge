package cmm.apps.adevinta.view.userlist.model

import cmm.apps.adevinta.view.R
import cmm.apps.designsystem.AdevintaCardInfoModel
import org.koin.core.component.KoinComponent

data class UserListUiState(
    val icon: Int = R.drawable.ic_logo,
    val userList: List<AdevintaCardInfoModel> = emptyList()
) : KoinComponent {

    fun createDefaultUserListUiState(): UserListUiState {
        return UserListUiState()
    }

    fun UserListUiState.loadMoreUsers() {
        val takeUserFromPosition = userList.size
        val takeUserToPosition = takeUserFromPosition + 10
        // get 10 new users
    }

}

sealed class UserListEffect {
    data class NavigateToUserDetails(val position: Int) : UserListEffect()
}