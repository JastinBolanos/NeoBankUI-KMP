package com.neobank.app.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.core.navigation.NeoBottomNavigationBar

@Suppress("UNUSED_PARAMETER")
@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToCards: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {},
    selectedTab: NavTab,
    onTabSelected: (NavTab) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8E9EB))
    ) {
        Text(
            text = "COMING SOON",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            NeoBottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    }
}