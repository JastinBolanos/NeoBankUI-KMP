package com.neobank.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.neobank.app.auth.presentation.LoginScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen()
    }
}