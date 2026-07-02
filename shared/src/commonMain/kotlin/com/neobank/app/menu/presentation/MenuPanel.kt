package com.neobank.app.menu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_menu_panel
import org.jetbrains.compose.resources.painterResource

@Composable
fun MenuPanel(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.85f)
            .clip(RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp))
    ) {
        Image(
            painter = painterResource(Res.drawable.bg_menu_panel),
            contentDescription = "Fondo del panel de menú",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}