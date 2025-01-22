package cmm.apps.adevinta.view.screens.userdetails.model

import cmm.apps.adevinta.domain.user.model.User
import java.time.Instant
import java.time.ZoneId
import org.koin.core.component.KoinComponent

data class UserDetailsUiState(
    val pictureUrl: String? = null,
    val gender: String = "",
    val name: String = "",
    val direction: String = "",
    val registeredDate: String = "",
    val email: String = ""
) : KoinComponent {

    fun createUserDetailsUiState(user: User): UserDetailsUiState {
        return UserDetailsUiState(
            pictureUrl = user.imageUrl,
            gender = user.gender,
            name = "${user.firstName} ${user.lastName}".trim(),
            direction = user.location.getFullAddress(),
            registeredDate = Instant.ofEpochMilli(user.registeredDateTime).atZone(ZoneId.systemDefault()).toString(),
            email = user.email
        )
    }

}