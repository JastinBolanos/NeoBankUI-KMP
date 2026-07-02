package com.neobank.app.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.ic_cards
import neobankui.shared.generated.resources.ic_history
import neobankui.shared.generated.resources.ic_nav_home
import org.jetbrains.compose.resources.painterResource

enum class NavTab {
    Home,
    History,
    Transfer,
    Cards,
    Profile
}

@Composable
fun NeoBottomNavigationBar(
    selectedTab: NavTab,
    isDarkMode: Boolean = false,
    onTabSelected: (NavTab) -> Unit
) {
    // --- LÓGICA DE COLORES DINÁMICOS ---
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val activeColor = Color(0xFF3F1D6B)
    val inactiveColor = if (isDarkMode) Color(0xFF8E8E93) else Color(0xFFA8A8A8)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Icono Casa
            val homeColor = if (selectedTab == NavTab.Home) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .clickable { onTabSelected(NavTab.Home) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_nav_home),
                    contentDescription = "Home",
                    tint = homeColor,
                    modifier = Modifier.size(26.dp)
                )
            }

            // 2. Icono Historial
            val historyColor = if(selectedTab == NavTab.History) activeColor else inactiveColor
            Box(
                modifier = Modifier.size(44.dp).clip(CircleShape).clickable { onTabSelected(NavTab.History) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_history),
                    contentDescription = "History",
                    tint = historyColor,
                    modifier = Modifier.size(26.dp)
                )
            }

            // 3. Icono Tarjeta
            val cardsColor = if (selectedTab == NavTab.Cards) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .clickable { onTabSelected(NavTab.Cards) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_cards),
                    contentDescription = "Cards",
                    tint = cardsColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // 4. Avatar
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(if (selectedTab == NavTab.Profile) activeColor else Color(0xFF2F80ED))
                    .clickable { onTabSelected(NavTab.Profile) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}