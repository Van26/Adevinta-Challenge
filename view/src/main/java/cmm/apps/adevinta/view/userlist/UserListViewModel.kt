package cmm.apps.adevinta.view.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmm.apps.adevinta.domain.user.GetSavedUserUseCase
import cmm.apps.adevinta.view.userlist.model.UserListEffect
import cmm.apps.adevinta.view.userlist.model.UserListUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val getMyEventListUseCase: GetSavedUserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    private val _effect: MutableSharedFlow<UserListEffect> = MutableSharedFlow(extraBufferCapacity = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val effect: SharedFlow<UserListEffect> = _effect.asSharedFlow()

    companion object {
        const val NEW_USERS_QUANTITY = 10
    }

    fun onUserItemClicked(position: Int) {
        _effect.tryEmit(UserListEffect.NavigateToUserDetails(position))
    }

    fun loadMoreUsers() {
        viewModelScope.launch {
            val result = getMyEventListUseCase(_uiState.value.userList.size + NEW_USERS_QUANTITY)
            result.onSuccess { userList ->
                _uiState.value = UserListUiState().createDefaultUserListUiState(userList)
            }.onFailure { error ->
                _uiState.value = UserListUiState()
            }.onNoConnectionError {
                _uiState.value = UserListUiState()
            }
        }
    }

}
