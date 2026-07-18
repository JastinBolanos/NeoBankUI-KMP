package com.neobank.app.transfer.presentation.components

import neobankui.shared.generated.resources.CinzelDecorative_Regular
import neobankui.shared.generated.resources.ic_dots_grid
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import neobankui.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

val CinzelDecorative: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.CinzelDecorative_Regular, FontWeight.Normal)
    )

@Composable
fun NoteField(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_dots_grid),
            contentDescription = "Add note",
            tint = Color.White.copy(alpha = 0.65f),
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "ADD NOTE",
            style = TextStyle(
                fontFamily = CinzelDecorative,
                fontWeight = FontWeight.Normal,
                fontSize = 15.63.sp,
                letterSpacing = (-0.25).sp,
                color = Color.White.copy(alpha = 0.65f)
            )
        )
    }
}