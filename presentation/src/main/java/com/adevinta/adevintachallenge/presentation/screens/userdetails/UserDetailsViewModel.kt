package com.adevinta.adevintachallenge.presentation.screens.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle

class UserDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val userId: String = savedStateHandle["userId"] ?: "-1"
}