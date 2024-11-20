package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.profile.ui.components.ProfileCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProfileCard(
            name = "Juan Pérez",
            dni = "30.123.456",
            email = "j****@gmail.com",
            alias = "juan123",
            cbu = "1234567890123456789012",
            onChangeAliasClick = { },
            onChangePasswordClick = { },
            onLogoutClick = { }
        )
    }
}

@PreviewScreenSizes
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

