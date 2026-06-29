package com.neobank.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.neobank.app.auth.presentation.WelcomeScreen
import com.neobank.app.cards.presentation.CardsScreen
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.home.presentation.HomeScreen
import com.neobank.app.profile.presentation.ProfileScreen
import com.neobank.app.transactions.presentation.TransactionHistoryScreen
import com.neobank.app.transfer.presentation.SendMoneyScreen

enum class Screen {
    Welcome,
    Home,
    History,
    Cards,
    SendMoney,
    Profile
}

private fun NavTab.toScreen(): Screen = when (this) {
    NavTab.Home    -> Screen.Home
    NavTab.History -> Screen.History
    NavTab.Cards   -> Screen.Cards
    NavTab.Profile -> Screen.Profile
    NavTab.Transfer-> Screen.Home
}

@Composable
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf(Screen.Welcome) }

        val onTabSelected: (NavTab) -> Unit = { tab ->
            currentScreen = tab.toScreen()
        }

        when (currentScreen) {
            Screen.Welcome -> {
                WelcomeScreen(onNavigateToHome = { currentScreen = Screen.Home })
            }

            Screen.Home -> {
                HomeScreen(
                    onNavigateToCards     = { currentScreen = Screen.Cards },
                    onNavigateToSendMoney = { currentScreen = Screen.SendMoney },
                    onNavigateToHistory   = { currentScreen = Screen.History },
                    onNavigateToProfile   = { currentScreen = Screen.Profile },
                    selectedTab           = NavTab.Home,
                    onTabSelected         = onTabSelected
                )
            }

            Screen.History -> {
                TransactionHistoryScreen(
                    onNavigateToHome    = { currentScreen = Screen.Home },
                    onNavigateToCards   = { currentScreen = Screen.Cards },
                    onNavigateToProfile = { currentScreen = Screen.Profile },
                    selectedTab         = NavTab.History,
                    onTabSelected       = onTabSelected
                )
            }

            Screen.Cards -> {
                CardsScreen(
                    onNavigateToHome    = { currentScreen = Screen.Home },
                    onNavigateToHistory = { currentScreen = Screen.History },
                    onNavigateToProfile = { currentScreen = Screen.Profile },
                    selectedTab         = NavTab.Cards,
                    onTabSelected       = onTabSelected
                )
            }

            Screen.SendMoney -> {
                SendMoneyScreen(onBackClick = { currentScreen = Screen.Home })
            }

            Screen.Profile -> {
                ProfileScreen(
                    onNavigateToHome    = { currentScreen = Screen.Home },
                    onNavigateToCards   = { currentScreen = Screen.Cards },
                    onNavigateToHistory = { currentScreen = Screen.History },
                    selectedTab         = NavTab.Profile,
                    onTabSelected       = onTabSelected
                )
            }
        }
    }
}