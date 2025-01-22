package cmm.apps.adevinta.view.screens.userdetails

import androidx.lifecycle.ViewModel
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.screens.userdetails.model.UserDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserDetailsViewModel(user: User) : ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailsUiState().createUserDetailsUiState(user))
    val uiState: StateFlow<UserDetailsUiState> = _uiState.asStateFlow()

}