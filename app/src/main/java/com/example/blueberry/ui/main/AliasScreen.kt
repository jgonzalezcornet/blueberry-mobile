package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.AliasCard
import com.example.blueberry.ui.components.ChangeAliasCard
import com.example.blueberry.ui.components.ScreenTitle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun AliasScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState
    var changeAliasModalOpen by rememberSaveable { mutableStateOf(false) }
    var refreshTrigger by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(refreshTrigger) {
        viewModel.getWalletDetails()
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(R.string.alias_title),
            onBackNavigation = onBackNavigation
        )
        AliasCard(
            balance = uiState.details?.balance.toString(),
            alias = uiState.details?.alias ?: "",
            cbu = uiState.details?.cbu ?: ""
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { changeAliasModalOpen = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.change_alias),
                    color = Color.White
                )
            }
        }
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
    }
}

@PreviewScreenSizes
@Composable
fun AliasScreenPreview() {
    AliasScreen()
}
