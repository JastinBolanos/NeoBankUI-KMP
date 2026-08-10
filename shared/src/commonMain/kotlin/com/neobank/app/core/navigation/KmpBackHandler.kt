package com.neobank.app.core.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun KmpBackHandler(enabled: Boolean = true, onBack: () -> Unit)