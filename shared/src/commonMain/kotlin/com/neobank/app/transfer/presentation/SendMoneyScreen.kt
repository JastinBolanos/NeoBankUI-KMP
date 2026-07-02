package com.neobank.app.transfer.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neobank.app.transfer.presentation.components.AccountSelector
import com.neobank.app.transfer.presentation.components.Keypad
import com.neobank.app.transfer.presentation.components.NoteField
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank
import neobankui.shared.generated.resources.ic_arrow_back_custom
import neobankui.shared.generated.resources.ic_user_list
import org.jetbrains.compose.resources.painterResource

@Composable
fun SendMoneyScreen(
    currentBalance: Double,
    onSendMoney: (Double) -> Unit,
    onBackClick: () -> Unit = {}
) {
    var amount by remember { mutableStateOf("0") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 66.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- ENCABEZADO ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back_custom),
                        contentDescription = "Volver",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "Send Money",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.size(44.dp))
            }

            Spacer(modifier = Modifier.height(48.dp))

            // --- MONTO ---
            Text(
                text = "$$amount",
                color = Color.White,
                fontSize = 55.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "no fees",
                color = Color.White.copy(alpha = 0.55f),
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // --- SELECTOR DE CUENTA ---
            AccountSelector(
                accountName = "Main",
                balance = "$${currentBalance}"
            )

            Spacer(modifier = Modifier.height(48.dp))

            // --- CAMPO DE NOTA ---
            NoteField()

            Spacer(modifier = Modifier.height(24.dp))

            // --- BOTÓN ENVIAR + ÍCONO ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            val amountToSend = amount.toDoubleOrNull() ?: 0.0
                            if (amountToSend > 0.0 && amountToSend <= currentBalance) {
                                onSendMoney(amountToSend)
                            }
                        },
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White.copy(alpha = 0.92f)
                ) {
                    Text(
                        text = "Send",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1A1A),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                Icon(
                    painter = painterResource(Res.drawable.ic_user_list),
                    contentDescription = "Seleccionar contacto",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- TECLADO NUMÉRICO ---
            Keypad(
                onNumberClick = { number ->
                    if (amount == "0") amount = number
                    else amount += number
                },
                onDotClick = {
                    if (!amount.contains(".")) amount += "."
                },
                onDeleteClick = {
                    amount = if (amount.length > 1) amount.dropLast(1) else "0"
                }
            )
        }
    }
}