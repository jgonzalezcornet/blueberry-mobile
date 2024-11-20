package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.theme.PrimaryBlue
import com.example.profile.ui.components.ProfileCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onChangeAliasClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onChangeAliasClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Text(
                    text = stringResource(R.string.change_alias),
                    color = Color.White
                )
            }
            Button(
                onClick = onChangePasswordClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Text(
                    text = stringResource(R.string.change_password),
                    color = Color.White
                )
            }
            Button(
                onClick = onLogoutClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Text(
                    text = stringResource(R.string.logout),
                    color = Color.White
                )
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

