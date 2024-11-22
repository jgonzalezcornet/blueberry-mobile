package com.example.blueberry.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.blueberry.R
import com.example.blueberry.data.model.Error
import com.example.blueberry.ui.components.ChangeAliasCard
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel
import com.example.profile.ui.components.ProfileCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onLogout: () -> Unit = {},
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_profile") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            initialized = true
            viewModel.initializeForm()
            viewModel.setFormValue("changeAliasModalOpen", "false")
        }
    }

    if(!uiState.isAuthenticated && !uiState.isFetching){
        onUnauthenticated()
    }

    var refreshTrigger by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(refreshTrigger) {
        viewModel.getCurrentUser()
        viewModel.getWalletDetails()
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                enabled = true,
                state = rememberScrollState())
            .padding(horizontal = getPadding())
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
            onChangeAliasClick = { viewModel.setFormValue("changeAliasModalOpen", "true") },
            onLogout = {
                viewModel.logout()
                onLogout()
            }
        )

        if (uiState.form?.get("changeAliasModalOpen") == "true") {
            ChangeAliasCard(
                onClose = { viewModel.setFormValue("changeAliasModalOpen", "false") },
                onConfirm = {
                    try {
                        viewModel.updateAlias(uiState.form?.get("newAlias") ?: "", {
                            viewModel.setFormValue("changeAliasModalOpen", "false")
                            refreshTrigger += 1
                        })
                    } catch(e: Exception) {
                        viewModel.setError(Error(400, e.message.toString()))
                    }
                },
                newAlias = uiState.form?.get("newAlias") ?: "",
                onValueChange = { key, value -> viewModel.setFormValue(key, value) },
            )
        }
    }
}