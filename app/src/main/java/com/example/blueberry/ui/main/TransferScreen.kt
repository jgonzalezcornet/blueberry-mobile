package com.example.blueberry.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.data.model.Payment
import com.example.blueberry.ui.components.PayTransferCard
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.TransferCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun TransferScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onTransferSuccess: () -> Unit = {},
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState
    if(!uiState.isAuthenticated){
        onUnauthenticated()
    }

    LaunchedEffect(Unit) {
        viewModel.getCards()
    }

    var transferStage by remember { mutableStateOf(0) }
    var destination by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            )
            .padding(horizontal = getPadding())
            .fillMaxWidth(),
    ) {
        if (transferStage == 0) {
            ScreenTitle(
                title = stringResource(R.string.transfer_title),
                onBackNavigation = onBackNavigation
            )

            TransferCard(
                onTransferConfirmed = { dest, amt, desc ->
                    destination = dest
                    description = desc
                    amount = amt
                    transferStage = 1
                }
            )
        } else if (transferStage == 1) {
            PayTransferCard(
                onCancel = { transferStage = 0 },
                destination = destination,
                amount = amount.toIntOrNull() ?: 0,
                availableCards = uiState.cards ?: listOf(),
                onPay = { method, cardId ->
                    viewModel.makePayment(
                        Payment(
                            amount = amount.toDouble(),
                            description = description,
                            type = method,
                            cardId = cardId,
                            receiverEmail = "johndoe@email.com"
                        )
                    )
                    onTransferSuccess()
                }
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun TransferScreenPreview() {
    TransferScreen()
}