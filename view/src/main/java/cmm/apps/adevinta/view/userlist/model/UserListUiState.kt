package cmm.apps.adevinta.view.userlist.model

import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.R
import cmm.apps.designsystem.AdevintaCardInfoModel
import org.koin.core.component.KoinComponent

data class UserListUiState(
    val icon: Int = R.drawable.ic_logo,
    val userList: List<AdevintaCardInfoModel> = emptyList()
) : KoinComponent {

    fun createDefaultUserListUiState(userList: List<User>): UserListUiState {
        return UserListUiState(userList = userList.map { it.toAdevintaCardInfoModel() })
    }

    private fun User.toAdevintaCardInfoModel() = AdevintaCardInfoModel(
        mainText = "$firstName $lastName",
        secondaryText = email,
        tertiaryText = phone,
        imageUrl = imageUrl
    )

}

sealed class UserListEffect {
    data class NavigateToUserDetails(val position: Int) : UserListEffect()
}