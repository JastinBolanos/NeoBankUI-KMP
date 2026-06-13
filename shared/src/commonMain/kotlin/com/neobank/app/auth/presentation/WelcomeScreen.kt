package com.neobank.app.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.ui.input.pointer.pointerInput
import com.neobank.app.auth.presentation.components.GlassCard
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank
import neobankui.shared.generated.resources.ic_fingerprint
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen(onNavigateToHome: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Fondo Premium NeoBank",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // --- TÍTULO SUPERIOR (Logo de la marca) ---
            Text(
                text = "AuraNova",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp,
                modifier = Modifier.padding(top = 48.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- ZONA DE TARJETAS ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                // Tarjeta 1: Current Balance (Atrás)
                GlassCard(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .fillMaxWidth(0.85f)
                        .height(200.dp)
                        .offset(x = 80.dp),
                    containerAlpha = 0.08f
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Parte Superior: Saldo
                        Column {
                            Text(
                                text = "Current Balance",
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$10 985,84",
                                color = Color.White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column {
                            Text(
                                text = "**** **** **** 1289",
                                color = Color.White,
                                fontSize = 16.sp,
                                letterSpacing = 3.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "09/25",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                letterSpacing = 2.sp
                            )
                        }
                    }
                }

                // Tarjeta 2: SBI Card (Frente)
                GlassCard(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth(0.9f)
                        .height(210.dp)
                        .offset(y = (-20).dp),
                    containerAlpha = 0.25f
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "SBI Card",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )

                        // Fila Inferior: Números, Nombre y Logo
                        Column {
                            Text(
                                text = "4521  7896  5412  3698",
                                color = Color.White,
                                fontSize = 18.sp,
                                letterSpacing = 2.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Text(
                                        text = "SAURABH KUMAR",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 11.sp
                                    )
                                    Text(
                                        text = "03/25",
                                        color = Color.White.copy(alpha = 0.7f),
                                        fontSize = 11.sp
                                    )
                                }
                                MastercardLogo()
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            // --- TEXTOS INFERIORES (El Saludo) ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp)
            ) {
                Text(
                    text = "Welcome back,",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 28.sp,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
                Text(
                    text = "Saurabh Kumar",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            // --- GATILLO BIOMÉTRICO ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // El Anillo del Escáner
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .background(
                            color = Color(0xFF14141E).copy(alpha = 0.6f),
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                        .border(
                            width = 2.dp,
                            brush = androidx.compose.ui.graphics.Brush.linearGradient(
                                colors = listOf(Color(0xFF40B143), Color(0xFF493B94))
                            ),
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    onNavigateToHome()
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_fingerprint),
                        contentDescription = "Desbloquear con huella",
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Use PIN code",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun MastercardLogo() {
    Box(
        modifier = Modifier
            .width(44.dp)
            .height(28.dp),
        contentAlignment = Alignment.Center
    ) {
        // Círculo Rojo (Izquierda)
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
                .background(Color(0xFFEB001B), shape = androidx.compose.foundation.shape.CircleShape)
        )
        // Círculo Amarillo/Naranja (Derecha)
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(28.dp)
                .background(Color(0xFFF79E1B).copy(alpha = 0.9f), shape = androidx.compose.foundation.shape.CircleShape)
        )
    }
}