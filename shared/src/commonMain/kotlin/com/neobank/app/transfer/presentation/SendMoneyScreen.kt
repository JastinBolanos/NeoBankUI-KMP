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

private fun formatCurrency(value: Double): String {
    val isWholeNumber = value % 1.0 == 0.0
    val integerPart = value.toLong().toString()
    val formattedInteger = integerPart.reversed().chunked(3).joinToString(",").reversed()

    return if (isWholeNumber) {
        formattedInteger
    } else {
        val decimalPart = value.toString().substringAfter(".")
        "$formattedInteger.$decimalPart"
    }
}

@Composable
fun SendMoneyScreen(
    currentBalance: Double,
    onSendMoney: (Double) -> Unit,
    onBackClick: () -> Unit = {}
) {
    var amount by remember { mutableStateOf("0") }
    val maxLimit = 10000.0

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 66.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- HEADER ---
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
                        contentDescription = "Back",
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

            // --- AMOUNT ---
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

            // --- ACCOUNT SELECTOR ---
            AccountSelector(
                accountName = "Main",
                balance = "$${formatCurrency(currentBalance)}"
            )

            Spacer(modifier = Modifier.height(48.dp))

            // --- NOTE FIELD ---
            NoteField()

            Spacer(modifier = Modifier.height(24.dp))

            // --- SEND BUTTON + ICON ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(28.dp))
                        .clickable {
                            val amountToSend = amount.toDoubleOrNull() ?: 0.0
                            if (amountToSend > 0.0 && amountToSend <= currentBalance && amountToSend <= maxLimit) {
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
                    contentDescription = "Select contact",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- KEYPAD ---
            Keypad(
                onNumberClick = { number ->
                    val newAmount = if (amount == "0") number else amount + number
                    if ((newAmount.toDoubleOrNull() ?: 0.0) <= maxLimit) {
                        amount = newAmount
                    }
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