package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.LoginCard
import com.example.blueberry.ui.components.TopBar

@Composable
fun LoginScreen() {
    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        LoginCard(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@PreviewScreenSizes
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

