package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.RegisterCard
import com.example.blueberry.ui.components.TopBar

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        RegisterCard(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@PreviewScreenSizes
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}

