package cmm.apps.adevinta.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.screens.userdetails.UserDetailsScreen
import cmm.apps.adevinta.view.screens.userlist.UserListScreen
import kotlin.reflect.typeOf
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.VisibleForTesting

sealed class Navigation {

    @Serializable
    data object UserListScreen : Navigation()

    @Serializable
    data class UserDetailsScreen(val user: User) : Navigation()

}

@Composable
fun AdevintaNavigationGraph(navigationController: NavHostController) {
    AdevintaNavHost(navigationController, Navigation.UserListScreen)
}

/**
 * Function created setting up the navigation graph from a given starting point. **DO NOT CALL FROM APP CODE**, it only exists for testing purposes.
 * */
@VisibleForTesting
@Composable
internal fun AdevintaNavHost(navigationController: NavHostController, startDestination: Navigation) {
    NavHost(navigationController, startDestination = startDestination) {
        userFlow(navigationController)
    }
}

private fun NavGraphBuilder.userFlow(navigationController: NavHostController) {
    composable<Navigation.UserListScreen> {
        UserListScreen(
            onUserClicked = { user ->
                navigationController.navigate(Navigation.UserDetailsScreen(user))
            }
        )
    }
    composable<Navigation.UserDetailsScreen>(
        typeMap = mapOf(typeOf<User>() to serializableType<User>())
    ) { backStackEntry ->
        UserDetailsScreen(
            user = backStackEntry.toRoute<Navigation.UserDetailsScreen>().user
        )
    }
}
