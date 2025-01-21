package com.calcula.adevintachallengetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adevinta.adevintachallenge.presentation.navigation.AppNavigation
import com.calcula.adevintachallengetest.ui.theme.AdevintaChallengeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdevintaChallengeTestTheme {
                AppNavigation()
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            }
        }
    }
}