package com.adevinta.adevintachallenge.presentation.navigation

sealed class AppScreens(val route: String) {
    data object UserList : AppScreens("userList")
    data object UserDetails : AppScreens("userDetails/{userId}") {
        fun createRoute(userId: String) = "userDetails/$userId"
    }
}