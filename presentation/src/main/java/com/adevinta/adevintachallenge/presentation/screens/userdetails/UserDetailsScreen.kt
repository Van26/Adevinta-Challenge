package com.adevinta.adevintachallenge.presentation.screens.userdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun UserDetailsScreen(
    navController: NavController,
    viewModel: UserDetailsViewModel = viewModel()
) {
    Column {
        Text(text = "User ${viewModel.userId}")
    }
}