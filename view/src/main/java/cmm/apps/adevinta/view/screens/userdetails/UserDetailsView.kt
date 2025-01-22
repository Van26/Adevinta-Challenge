package cmm.apps.adevinta.view.screens.userdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.Screen
import cmm.apps.adevinta.view.screens.userdetails.model.UserDetailsUiState
import cmm.apps.adevinta.view.theme.AdevintaTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Screen
@Composable
fun UserDetailsScreen(
    user: User,
    udvm: UserDetailsViewModel = koinViewModel(parameters = { parametersOf(user) })
) {
    val uiState by udvm.uiState.collectAsStateWithLifecycle()

    AdevintaTheme {
        UserDetailsView(uiState)
    }
}

@Composable
fun UserDetailsView(uiState: UserDetailsUiState) {

}