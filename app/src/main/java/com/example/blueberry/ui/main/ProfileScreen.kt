package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.profile.ui.components.ProfileCard
import com.example.blueberry.ui.components.ChangeAliasCard
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.blueberry.ui.components.ChangePasswordCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {}
) {
    var changeAliasModalOpen by rememberSaveable { mutableStateOf(false) }
    var changePasswordModalOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProfileCard(
            name = "Juan Pérez",
            dni = "30.123.456",
            email = "j****@gmail.com",
            alias = "juan123",
            cbu = "1234567890123456789012",
            onChangeAliasClick = { changeAliasModalOpen = true },
            onChangePasswordClick = { changePasswordModalOpen = true },
            onLogoutClick = { onLogout() }
        )
        if(changeAliasModalOpen){
            ChangeAliasCard(
                onClose = { changeAliasModalOpen = false }
            )
        }
        if(changePasswordModalOpen){
            ChangePasswordCard(
                onClose = { changePasswordModalOpen = false }
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}

