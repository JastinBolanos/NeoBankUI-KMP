package com.neobank.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.neobank.app.auth.presentation.WelcomeScreen
import com.neobank.app.cards.presentation.CardsScreen
import com.neobank.app.home.presentation.HomeScreen

enum class Screen {
    Welcome,
    Home,
    Cards
}


@Composable
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf(Screen.Welcome) }

        when (currentScreen) {
            Screen.Welcome -> {
                WelcomeScreen(
                    onNavigateToHome = {
                        currentScreen = Screen.Home
                    }
                )
            }
            Screen.Home -> {
                HomeScreen(
                    onNavigateToCards = {
                        currentScreen = Screen.Cards
                    }
                )
            }
            Screen.Cards -> {
                CardsScreen(
                    onNavigateToHome = {
                        currentScreen = Screen.Home
                    }
                )
            }
        }
    }
}