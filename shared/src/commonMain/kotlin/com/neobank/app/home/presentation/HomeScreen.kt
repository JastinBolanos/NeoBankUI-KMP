package com.neobank.app.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Person

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // 1. EL FONDO
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. EL CONTENIDO PRINCIPAL
        Column(modifier = Modifier.fillMaxSize()) {

            // --- MITAD SUPERIOR ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.3f)
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                TopBarSection(modifier = Modifier.padding(horizontal = 24.dp))
                Spacer(modifier = Modifier.height(40.dp))
                BalanceSection()
                Spacer(modifier = Modifier.height(40.dp))
                QuickActionsSection(modifier = Modifier.padding(horizontal = 24.dp))
                Spacer(modifier = Modifier.height(32.dp))
                PromoCardsSection()
            }

            // --- MITAD INFERIOR ---
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color(0xFFF8F9FA),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {
                TransactionsSection(bottomPadding = 90.dp)
            }
        }

        // 3. LA BARRA DE NAVEGACIÓN INFERIOR (Flotando por encima)
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomNavigationBar()
        }
    }
}


// --- SUB-COMPONENTES PARA MANTENER EL CÓDIGO LIMPIO ---
@Composable
private fun TopBarSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hi John Willian",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            Text(
                text = "Welcome Back",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Botón de Notificaciones Glassmorphism
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun BalanceSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$2,340.00",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.sp
        )
        Text(
            text = "Total Balance",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun QuickActionsSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Usamos íconos nativos de Material Design como placeholders temporales
        ActionItem(icon = Icons.Default.SwapHoriz, label = "Transfer\nmoney")
        ActionItem(icon = Icons.Default.CreditCard, label = "Cards")
        ActionItem(icon = Icons.Default.InsertChart, label = "My bonuses")
        ActionItem(icon = Icons.Default.GridView, label = "More")
    }
}

@Composable
private fun ActionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(76.dp)
    ) {
        // La caja de cristal (Glassmorphism)
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // El texto descriptivo
        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun PromoCardsSection() {
    androidx.compose.foundation.lazy.LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(3) {
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(110.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
private fun TransactionsSection(bottomPadding: androidx.compose.ui.unit.Dp = 0.dp) {

    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 24.dp,
            bottom = bottomPadding
        )
    ) {
        item {
            Text(
                text = "TRANSACTIONS",
                color = Color(0xFF2E3A59),
                fontSize = 14.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "October 24, 2023",
                color = Color(0xFF1A1A1A),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item { TransactionItem("W", "Webflow", "Outcoming transfer", "- $45", Color(0xFF2F80ED)) }
        item { TransactionItem("S", "Sketch", "Annual withdrawal of funds", "- $79", Color(0xFFF2C94C)) }
        item { TransactionItem("Y", "Youtube", "Annual withdrawal of funds", "- $15", Color(0xFFEB5757)) }
        item { TransactionItem("U", "Unsplash", "Outcoming transfer", "- $9", Color(0xFF333333)) }
    }
}

@Composable
private fun TransactionItem(
    initial: String,
    title: String,
    subtitle: String,
    amount: String,
    iconBgColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ícono circular simulando los logos de Webflow, Sketch, etc.
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initial,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Textos del centro
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color(0xFF1A1A1A),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = Color.Gray,
                fontSize = 13.sp
            )
        }

        // Monto a la derecha
        Text(
            text = amount,
            color = Color(0xFF1A1A1A),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun BottomNavigationBar() {
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
            // 1. Icono Casa (Activo - Color Morado)
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color(0xFF493B94),
                modifier = Modifier.size(28.dp)
            )

            // 2. Icono Transferir (Inactivo - Gris)
            Icon(
                imageVector = Icons.Default.SwapHoriz,
                contentDescription = "Transfer",
                tint = Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.size(28.dp)
            )

            // 3. Icono Billetera/Cards (Inactivo - Gris)
            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = "Cards",
                tint = Color.Gray.copy(alpha = 0.5f),
                modifier = Modifier.size(28.dp)
            )

            // 4. Icono Perfil (Simulación de Avatar con fondo azul)
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