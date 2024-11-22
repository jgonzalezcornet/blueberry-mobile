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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.R
import com.example.blueberry.data.model.Payment
import com.example.blueberry.ui.components.PayTransferCard
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.TransferCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.data.model.Error

@Composable
fun TransferScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onTransferSuccess: () -> Unit = {},
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_transfer") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            initialized = true
            viewModel.initializeForm()
            viewModel.setFormValue("transferStage", "0")
        }
    }

    if(!uiState.isAuthenticated && !uiState.isFetching){
        onUnauthenticated()
    }

    LaunchedEffect(Unit) {
        viewModel.getCards()
    }

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
        if (uiState.form?.get("transferStage") == "0") {
            ScreenTitle(
                title = stringResource(R.string.transfer_title),
                onBackNavigation = onBackNavigation
            )

            TransferCard(
                onTransferConfirmed = { viewModel.setFormValue("transferStage", "1") },
                destination = uiState.form["destination"] ?: "",
                amount = uiState.form["amount"] ?: "",
                description = uiState.form["description"] ?: "",
                onValueChange = { key, value -> viewModel.setFormValue(key, value) },
            )
        } else if (uiState.form?.get("transferStage") == "1") {
            PayTransferCard(
                onCancel = { viewModel.setFormValue("transferStage", "0") },
                destination = uiState.form["destination"] ?: "",
                amount = uiState.form["amount"]?.toIntOrNull() ?: 0,
                availableCards = uiState.cards ?: listOf(),
                selectedPaymentMethod = uiState.form["selectedPaymentMethod"] ?: "",
                onValueChange = { key, value -> viewModel.setFormValue(key, value) },
                onPay = {
                    try {
                        val type = uiState.form["selectedPaymentMethod"] ?: ""
                        viewModel.makePayment(
                            Payment(
                                amount = uiState.form["amount"]?.toDoubleOrNull() ?: 0.0,
                                description = uiState.form["description"] ?: "",
                                type = when (type) { "account_balance" -> "BALANCE" else -> "CARD" },
                                cardId = uiState.form["selectedCardId"]?.toIntOrNull() ?: 0,
                                receiverEmail = uiState.form["destination"] ?: ""
                            ),
                            onTransferSuccess
                        )
                    } catch(e: Exception) {
                        viewModel.setError(Error(400, e.message.toString()))
                    }
                }
            )
        }
    }
}