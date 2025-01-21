package com.adevinta.adevintachallenge.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.adevinta.adevintachallenge.presentation.navigation.AppScreens.UserDetails

@Composable
fun UserListScreen(navController: NavController) {
    Column {
        Text(
            text = "User 1",
            modifier = Modifier.clickable {
                navController.navigate(UserDetails.createRoute("1"))
            }
        )
        Text(
            text = "User 2",
            modifier = Modifier.clickable {
                navController.navigate(UserDetails.createRoute("2"))
            }
        )
    }
}