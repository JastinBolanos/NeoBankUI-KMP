package com.neobank.app.transfer.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.blur
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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.animation.core.animateFloatAsState

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
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 25.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.35f))
                        .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape)
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back_custom),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .size(18.dp)
                            .offset(x = (-1).dp, y = 0.dp)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val contacts = remember {
                    listOf(
                        Pair("Sarah Miller", Color(0xFF6B11FF)),
                        Pair("Michael Scott", Color(0xFF1565C0)),
                        Pair("Elena Cruz", Color(0xFFE94D4D)),
                        Pair("David Chen", Color(0xFF00C853))
                    )
                }

                var expandedMenu by remember { mutableStateOf(false) }
                var selectedContact by remember { mutableStateOf(contacts[0]) }

                Box {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.White.copy(alpha = 0.08f))
                            .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(30.dp))
                            .clickable { expandedMenu = true }
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(selectedContact.second),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = selectedContact.first.take(1),
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "To: ", color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)

                        Text(
                            text = selectedContact.first,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        val rotationAngle by animateFloatAsState(
                            targetValue = if (expandedMenu) 90f else 270f,
                            label = "arrowRotation"
                        )
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back_custom),
                            contentDescription = "Change",
                            tint = Color.White.copy(alpha = 0.6f),
                            modifier = Modifier
                                .size(12.dp)
                                .graphicsLayer { rotationZ = rotationAngle }
                        )
                    }

                    DropdownMenu(
                        expanded = expandedMenu,
                        onDismissRequest = { expandedMenu = false },
                        modifier = Modifier
                            .background(Color(0xFF1E1E1E).copy(alpha = 0.95f))
                            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        contacts.forEach { contact ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clip(CircleShape)
                                                .background(contact.second),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = contact.first.take(1),
                                                color = Color.White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = contact.first,
                                            color = Color.White,
                                            fontSize = 14.sp
                                        )
                                    }
                                },
                                onClick = {
                                    selectedContact = contact
                                    expandedMenu = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$$amount",
                        color = Color.White,
                        fontSize = 54.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "no fees",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                AccountSelector(
                    accountName = "Main",
                    balance = "$${formatCurrency(currentBalance)}"
                )

                Spacer(modifier = Modifier.height(24.dp))

                NoteField()

                Spacer(modifier = Modifier.height(24.dp))

                val isAmountValid = amount != "0"

                val buttonColor by animateColorAsState(
                    targetValue = if (isAmountValid) Color.White else Color.White.copy(alpha = 0.15f),
                    animationSpec = tween(300),
                    label = "buttonColor"
                )
                val textColor by animateColorAsState(
                    targetValue = if (isAmountValid) Color(0xFF1A1A1A) else Color.White.copy(alpha = 0.4f),
                    animationSpec = tween(300),
                    label = "textColor"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(28.dp))
                            .clickable(enabled = isAmountValid) {
                                val amountToSend = amount.toDoubleOrNull() ?: 0.0
                                if (amountToSend > 0.0 && amountToSend <= currentBalance && amountToSend <= maxLimit) {
                                    onSendMoney(amountToSend)
                                }
                            },
                        shape = RoundedCornerShape(28.dp),
                        color = buttonColor
                    ) {
                        Text(
                            text = "Send",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Black.copy(alpha = 0.35f))
                            .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                            .clickable { /* Acción para contactos */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_user_list),
                            contentDescription = "Select contact",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

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
}