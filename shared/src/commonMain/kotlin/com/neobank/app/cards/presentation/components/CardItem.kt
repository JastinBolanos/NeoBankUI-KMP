package com.neobank.app.cards.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardItem(
    balance: String,
    cardNumber: String,
    expiryDate: String,
    isSelected: Boolean
) {
    // Efecto Glassmorphism: Fondo blanco con transparencia
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(170.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .then(
                if (isSelected) Modifier else Modifier.scale(0.95f)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. Label del saldo
            Text(
                text = "Current Balance",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )

            // 2. Monto grande
            Text(
                text = balance,
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            // 3. Número de tarjeta y Fecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = cardNumber,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                )

                // 4. Fecha de expiración
                Text(
                    text = expiryDate,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }

            // 5. Logo de la tarjeta (Placeholder genérico estilo Mastercard)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFFF0000))
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFFFA500))
                        .offset(x = (-8).dp)
                )
            }
        }
    }
}

// Función de extensión auxiliar para simular un efecto de escala si no está seleccionada
private fun Modifier.scale(scale: Float): Modifier = this.then(
    Modifier.graphicsLayer {
        this.scaleX = scale
        this.scaleY = scale
    }
)