package com.adevinta.adevintachallenge.presentation.navigation

import com.adevinta.adevintachallenge.presentation.screens.home.UserListScreen
import com.adevinta.adevintachallenge.presentation.screens.userdetails.UserDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.UserList.route) {
        composable(route = AppScreens.UserList.route) {
            UserListScreen(navController = navController)
        }
        composable(
            route = AppScreens.UserDetails.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {
            UserDetailsScreen(navController = navController)
        }
    }
}