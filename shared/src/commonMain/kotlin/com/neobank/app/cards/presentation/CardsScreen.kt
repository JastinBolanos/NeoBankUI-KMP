package com.neobank.app.cards.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neobank.app.cards.presentation.components.CardItem
import com.neobank.app.core.navigation.NavTab
import com.neobank.app.core.navigation.NeoBottomNavigationBar
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank
import neobankui.shared.generated.resources.ic_bell
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardsScreen(
    onNavigateToHome: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // 1. EL FONDO DEGRADADO
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. CONTENIDO PRINCIPAL
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // --- CABECERA: TÍTULO "Cards" + CAMPANITA ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cards",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // La campanita, idéntica a la del Home
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

            // Espacio entre el título y el carrusel
            Spacer(modifier = Modifier.height(32.dp))

            // --- CARRUSEL DE TARJETAS ---
            val cards = listOf(
                Triple("$5 750,20", "**** **** **** 1289", "09/25"),
                Triple("$1 200,50", "**** **** **** 4923", "01/26"),
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(cards) { index, card ->
                    CardItem(
                        balance = card.first,
                        cardNumber = card.second,
                        expiryDate = card.third,
                        isSelected = index == 0
                    )
                }
            }

            // --- INDICADORES DE PAGINACIÓN ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.3f))
                )
            }
        }

        // 3. PANEL INFERIOR (CARD DETAILS)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter),
            color = Color(0xFFF8F9FA),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, top = 24.dp, bottom = 100.dp)
            ) {
                Text(
                    text = "CARD DETAILS",
                    color = Color(0xFF2E3A59),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 2.sp
                )
            }
        }

        // 4. BARRA DE NAVEGACIÓN INFERIOR
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            NeoBottomNavigationBar(
                selectedTab = NavTab.Cards,
                onTabSelected = { tab ->
                    when (tab) {
                        NavTab.Home -> onNavigateToHome()
                        NavTab.Cards -> { /* Ya estamos aquí */ }
                        else -> { /* Lógica futura */ }
                    }
                }
            )
        }
    }
}