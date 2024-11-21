package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.ChangeAliasCard
import com.example.blueberry.ui.components.PaddedContent
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.home.HomeViewModel
import com.example.profile.ui.components.ProfileCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onLogout: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    PaddedContent {
        val uiState = viewModel.uiState

        var changeAliasModalOpen by rememberSaveable { mutableStateOf(false) }
        var refreshTrigger by rememberSaveable { mutableStateOf(0) }

        LaunchedEffect(refreshTrigger) {
            viewModel.getCurrentUser()
            viewModel.getWalletDetails()
        }


        Column(
            modifier = modifier.fillMaxSize()
        ) {
            ScreenTitle(
                title = stringResource(R.string.profile_title),
                onBackNavigation = onBackNavigation
            )

            ProfileCard(
                firstName = uiState.currentUser?.firstName ?: "",
                lastName = uiState.currentUser?.lastName ?: "",
                email = uiState.currentUser?.email ?: "",
                alias = uiState.details?.alias ?: "",
                cbu = uiState.details?.cbu ?: "",
                onChangeAliasClick = { changeAliasModalOpen = true },
                onLogout = {
                    viewModel.logout()
                    onLogout()
                }
            )
            if (changeAliasModalOpen) {
                ChangeAliasCard(
                    onClose = { changeAliasModalOpen = false },
                    onConfirm = { newAlias ->
                        viewModel.updateAlias(newAlias)
                        changeAliasModalOpen = false
                        refreshTrigger += 1
                    }
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

