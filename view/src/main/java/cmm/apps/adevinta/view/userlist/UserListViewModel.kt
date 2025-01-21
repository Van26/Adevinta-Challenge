package cmm.apps.adevinta.view.userlist

import androidx.lifecycle.ViewModel
import cmm.apps.adevinta.view.userlist.model.UserListEffect
import cmm.apps.adevinta.view.userlist.model.UserListUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class UserListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState().createDefaultUserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    private val _effect: MutableSharedFlow<UserListEffect> = MutableSharedFlow(extraBufferCapacity = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val effect: SharedFlow<UserListEffect> = _effect.asSharedFlow()

    fun onUserItemClicked(position: Int) {
        _effect.tryEmit(UserListEffect.NavigateToUserDetails(position))
    }

}
