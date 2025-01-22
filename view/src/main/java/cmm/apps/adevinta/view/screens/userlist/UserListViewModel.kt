package cmm.apps.adevinta.view.screens.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmm.apps.adevinta.domain.user.GetSavedUserUseCase
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.screens.userlist.model.UserListEffect
import cmm.apps.adevinta.view.screens.userlist.model.UserListUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val getUserListUseCase: GetSavedUserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    private val _effect: MutableSharedFlow<UserListEffect> = MutableSharedFlow(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val effect: SharedFlow<UserListEffect> = _effect.asSharedFlow()

    private var userListOnScreen: List<User> = emptyList()

    companion object {
        const val NEW_USERS_QUANTITY = 10
    }

    fun onUserItemClicked(position: Int) {
        userListOnScreen.getOrNull(position)?.let {
            _effect.tryEmit(UserListEffect.NavigateToUserDetails(it))
        }
    }

    fun loadMoreUsers() {
        viewModelScope.launch {
            val result = getUserListUseCase(_uiState.value.userList.size + NEW_USERS_QUANTITY)
            result.onSuccess { userList ->
                _uiState.value = UserListUiState().createDefaultUserListUiState(userList)
                userListOnScreen = userList
            }.onFailure { error ->
                _uiState.value = UserListUiState()
            }.onNoConnectionError {
                _uiState.value = UserListUiState()
            }
        }
    }

}
