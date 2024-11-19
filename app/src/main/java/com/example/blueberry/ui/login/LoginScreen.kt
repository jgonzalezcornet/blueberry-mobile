package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.LoginCard
import com.example.blueberry.ui.components.TopBar

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}
) {
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        LoginCard(
            modifier = Modifier.padding(paddingValues),
            onNavigateToRegister = onNavigateToRegister,
            onLoginSuccess = onLoginSuccess
        )
    }
}

@PreviewScreenSizes
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

