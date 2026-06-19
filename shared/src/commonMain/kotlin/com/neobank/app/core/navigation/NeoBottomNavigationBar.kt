package com.neobank.app.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import neobankui.shared.generated.resources.ic_nav_home
import neobankui.shared.generated.resources.ic_transfer
import neobankui.shared.generated.resources.ic_cards
import org.jetbrains.compose.resources.painterResource

enum class NavTab {
    Home, Transfer, Cards, Profile
}

@Composable
fun NeoBottomNavigationBar(
    selectedTab: NavTab,
    onTabSelected: (NavTab) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
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
            val homeColor = if (selectedTab == NavTab.Home) Color(0xFF3F1D6B) else Color(0xFFA8A8A8)
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

            // 2. Icono Transferir
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .clickable { onTabSelected(NavTab.Transfer) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_transfer),
                    contentDescription = "Transfer",
                    tint = Color(0xFFA8A8A8),
                    modifier = Modifier.size(33.dp)
                )
            }

            // 3. Icono Tarjeta
            val cardsColor = if (selectedTab == NavTab.Cards) Color(0xFF3F1D6B) else Color(0xFFA8A8A8)
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
                    .background(Color(0xFF2F80ED)),
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