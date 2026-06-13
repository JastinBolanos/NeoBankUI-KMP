package com.neobank.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.neobank.app.auth.presentation.WelcomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        WelcomeScreen()
    }
}