package com.neobank.app.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import neobankui.shared.generated.resources.ic_more
import neobankui.shared.generated.resources.ic_bonuses
import neobankui.shared.generated.resources.ic_cards
import neobankui.shared.generated.resources.ic_transfer
import neobankui.shared.generated.resources.ic_bell
import neobankui.shared.generated.resources.ic_google
import neobankui.shared.generated.resources.ic_youtube
import neobankui.shared.generated.resources.ic_facebook
import neobankui.shared.generated.resources.ic_visa
import neobankui.shared.generated.resources.ic_claro
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.Icon
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.core.navigation.NeoBottomNavigationBar
import neobankui.shared.generated.resources.ic_scanner

@Composable
fun HomeScreen(
    onNavigateToCards: () -> Unit,
    onNavigateToSendMoney: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {}
) {
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
                    .weight(1.45f)
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                TopBarSection(modifier = Modifier.padding(horizontal = 24.dp))

                Spacer(modifier = Modifier.height(40.dp))

                BalanceSection()

                Spacer(modifier = Modifier.height(40.dp))

                QuickActionsSection(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onCardsClick = onNavigateToCards,
                    onTransferClick = onNavigateToSendMoney
                )

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
            NeoBottomNavigationBar(
                selectedTab = NavTab.Home,
                onTabSelected = { tab ->
                    when (tab) {
                        NavTab.Cards -> onNavigateToCards()
                        NavTab.History -> onNavigateToHistory()
                        NavTab.Home -> { /* Ya estamos aquí */ }
                        NavTab.Profile -> { /* Lógica futura */ }
                        NavTab.Transfer -> { /* Solo se usa desde el botón superior */ }
                    }
                }
            )
        }
    }
}


// --- SUB-COMPONENTES ---
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono ESCÁNER
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_scanner),
                    contentDescription = "Escanear código",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            // Icono NOTIFICACIONES
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_bell),
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
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
private fun QuickActionsSection(
    modifier: Modifier = Modifier,
    onCardsClick: () -> Unit,
    onTransferClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_transfer),
            label = "Transfer",
            onClick = onTransferClick
        )

        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_cards),
            label = "Cards",
            onClick = onCardsClick
        )

        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_bonuses),
            label = "My bonuses",
            onClick = { /* Lógica futura */ }
        )

        ActionItemPainter(
            painter = painterResource(Res.drawable.ic_more),
            label = "More",
            onClick = { /* Lógica futura */ }
        )
    }
}

@Composable
private fun ActionItemPainter(
    painter: androidx.compose.ui.graphics.painter.Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(76.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.1f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

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
                color = Color(0xFF243355),
                fontSize = 16.sp,
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

        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_google),
                title = "Google",
                subtitle = "Google Workspace Subscription",
                amount = "- $12.00"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_youtube),
                title = "YouTube",
                subtitle = "YouTube Premium Family",
                amount = "- $11.99"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_facebook),
                title = "Facebook",
                subtitle = "Ads Campaign Billing",
                amount = "- $50.00"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_visa),
                title = "Visa",
                subtitle = "Credit Card Payment",
                amount = "- $150.00"
            )
        }
        item {
            TransactionItem(
                painter = painterResource(Res.drawable.ic_claro),
                title = "Claro",
                subtitle = "Monthly Internet Bill",
                amount = "- $35.00"
            )
        }
    }
}

@Composable
private fun TransactionItem(
    painter: androidx.compose.ui.graphics.painter.Painter,
    title: String,
    subtitle: String,
    amount: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. EL CONTENEDOR
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            // 2. EL LOGO
            Image(
                painter = painter,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(0.65f),
                contentScale = ContentScale.Fit
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
                fontSize = 13.sp,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
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