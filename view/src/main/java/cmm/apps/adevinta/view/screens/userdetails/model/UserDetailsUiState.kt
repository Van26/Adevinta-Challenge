package cmm.apps.adevinta.view.screens.userdetails.model

import cmm.apps.adevinta.domain.user.model.User
import org.koin.core.component.KoinComponent

data class UserDetailsUiState(
    val name: String = "",
    val direction: String = ""
) : KoinComponent {

    fun createUserDetailsUiState(user: User): UserDetailsUiState {
        return UserDetailsUiState(
            user.firstName,
            user.location.getFullAddress()
        )
    }

}