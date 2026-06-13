package com.neobank.app.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import neobankui.shared.generated.resources.Res
import neobankui.shared.generated.resources.bg_neobank

@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(Res.drawable.bg_neobank),
            contentDescription = "Fondo Premium NeoBank",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
}
