package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.profile.ui.components.ProfileCard
import com.example.blueberry.ui.components.ChangeAliasCard
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.ui.components.ChangePasswordCard
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.MyApplication

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState
    var changeAliasModalOpen by rememberSaveable { mutableStateOf(false) }
    var changePasswordModalOpen by rememberSaveable { mutableStateOf(false) }
    var refreshTrigger by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(refreshTrigger) {
        viewModel.getCurrentUser()
        viewModel.getWalletDetails()
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProfileCard(
            firstName = uiState.currentUser?.firstName ?: "",
            lastName = uiState.currentUser?.lastName ?: "",
            email = uiState.currentUser?.email ?: "",
            alias = uiState.details?.alias ?: "",
            cbu = uiState.details?.cbu ?: "",
            onChangeAliasClick = { changeAliasModalOpen = true },
            onChangePasswordClick = { changePasswordModalOpen = true },
            onLogout = {
                viewModel.logout()
                onLogout()
            }
        )
        if(changeAliasModalOpen){
            ChangeAliasCard(
                onClose = { changeAliasModalOpen = false },
                onConfirm = { newAlias ->
                    viewModel.updateAlias(newAlias)
                    changeAliasModalOpen = false
                    refreshTrigger += 1
                }
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

