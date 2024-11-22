package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueberry.R
import com.example.blueberry.ui.components.LinkCard
import com.example.blueberry.ui.components.LinkGeneratedCard
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import androidx.compose.ui.platform.LocalContext

@Composable
fun LinkScreen(
    modifier: Modifier = Modifier,
    onUnauthenticated: () -> Unit = {},
    onBackNavigation: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
        val uiState = viewModel.uiState

        if(!uiState.isAuthenticated){
            onUnauthenticated()
        }

        var linkModalOpen by rememberSaveable { mutableStateOf(false) }
        var amount by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding())
        ) {
            ScreenTitle(
                title = stringResource(R.string.link_screen_title),
                onBackNavigation = onBackNavigation
            )
            LinkCard(
                onGenerateLink = { newAmount ->
                    amount = newAmount
                    linkModalOpen = true
                }
            )
            if (linkModalOpen) {
                LinkGeneratedCard(
                    amount = amount,
                    onClose = { linkModalOpen = false }
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun LinkScreenPreview() {
    LinkScreen()
}

