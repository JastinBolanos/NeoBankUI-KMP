package com.neobank.app.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.core.navigation.NeoBottomNavigationBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.ui.layout.ContentScale
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_profile
import org.jetbrains.compose.resources.painterResource

val DarkBackground = Color.Black
val CardBackground = Color(0xFF28282A)
val TextGray = Color(0xFFA0A0A5)

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
            .background(DarkBackground)
    ) {
        // --- 1. FONDO DEGRADADO ---
        Image(
            painter = painterResource(Res.drawable.bg_profile),
            contentDescription = "Fondo de perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // --- CONTENIDO DESLIZABLE ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 1. TOP BAR (Botón X y Botón Upgrade)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón Cerrar (X)
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onNavigateToHome() }
                )

                // Botón Upgrade
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardBackground)
                        .clickable { /* Lógica Upgrade */ }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Upgrade",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Upgrade",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. SECCIÓN DE USUARIO (Foto, Nombre, Username)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Foto de perfil circular
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre
                Text(
                    text = "Saurabh Kumar",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Username y QR
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "@ntan97",
                        color = TextGray,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "QR Code",
                        tint = TextGray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. TARJETAS DE ACCIÓN (Premium y Referrals)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tarjeta 1: Premium
                ActionCard(
                    modifier = Modifier.weight(1f),
                    iconContent = { StackedCardsIcon() },
                    title = "Premium",
                    subtitle = "Your plan"
                )

                // Tarjeta 2: Referrals
                ActionCard(
                    modifier = Modifier.weight(1f),
                    iconContent = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Referrals",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    title = "Referrals",
                    subtitle = "Invite & earn rewards"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 4. BLOQUE DE AJUSTES 1
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardBackground)
            ) {
                SettingsRow(icon = Icons.Default.Info, title = "Help")
                SettingsRow(icon = Icons.Default.Person, title = "Account")
                SettingsRow(icon = Icons.Default.List, title = "Documents & statements")
                SettingsRow(icon = Icons.Default.Star, title = "Learn")
                SettingsRow(icon = Icons.Default.Email, title = "Inbox")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 5. BLOQUE DE AJUSTES 2
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardBackground)
            ) {
                SettingsRow(icon = Icons.Default.Lock, title = "Security")
                SettingsRow(icon = Icons.Default.Notifications, title = "Notification settings")
                // --- NUEVAS OPCIONES DE LA IMAGEN ---
                SettingsRow(icon = Icons.Default.Brush, title = "Appearance") // Ícono nativo más cercano a diseño
                SettingsRow(icon = Icons.Default.Star, title = "New features") // Ícono nativo para novedades
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 6. BLOQUE DE AJUSTES 3 (About & Log out)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardBackground)
            ) {
                SettingsRow(icon = Icons.Default.Info, title = "About us")
                SettingsRow(icon = Icons.Default.ExitToApp, title = "Log out")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 7. FOOTER DE VERSIÓN
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Version 10.67",
                    color = TextGray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Neobank Technologies Peru SAC",
                    color = TextGray,
                    fontSize = 12.sp
                )
            }
        }

        // --- BARRA DE NAVEGACIÓN INFERIOR ---
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            NeoBottomNavigationBar(
                selectedTab = selectedTab,
                isDarkMode = true,
                onTabSelected = onTabSelected
            )
        }
    }
}

// --- COMPONENTES REUTILIZABLES PARA ESTA PANTALLA ---

@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    iconContent: @Composable () -> Unit,
    title: String,
    subtitle: String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CardBackground)
            .clickable { /* Acción */ }
            .padding(16.dp)
    ) {
        iconContent()
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = subtitle,
            color = TextGray,
            fontSize = 13.sp
        )
    }
}

@Composable
fun SettingsRow(
    icon: ImageVector,
    title: String,
    badgeCount: Int = 0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acción */ }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        if (badgeCount > 0) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE94D4D)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badgeCount.toString(),
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun StackedCardsIcon() {
    Box(modifier = Modifier.size(28.dp, 24.dp)) {
        // Tarjeta trasera
        Box(
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp)
                .size(22.dp, 16.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .border(0.5.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(3.dp))
        )
        // Tarjeta delantera
        Box(
            modifier = Modifier
                .offset(x = 6.dp, y = 8.dp)
                .size(22.dp, 16.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(Color.White.copy(alpha = 0.35f))
                .border(0.5.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(3.dp))
        )
    }
}