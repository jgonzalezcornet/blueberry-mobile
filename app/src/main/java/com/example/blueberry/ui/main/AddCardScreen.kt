package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.R
import com.example.blueberry.data.model.Card
import com.example.blueberry.data.model.CardType
import com.example.blueberry.data.model.Error
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.cards.AddCardCard
import com.example.blueberry.ui.components.cards.BigCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.utils.formatDate


@Composable
fun AddCardScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onAddCardSuccess: () -> Unit = {},
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_add_card") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            initialized = true
            viewModel.initializeForm()
            viewModel.setFormValue("showBack", "false")
        }
    }

        if(!uiState.isAuthenticated && !uiState.isFetching){
            onUnauthenticated()
        }


        val formattedCardNumber = uiState.form?.get("cardNumber")?.chunked(4)?.joinToString(" ")

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = getPadding())
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
        ) {
            ScreenTitle(
                title = stringResource(R.string.add_card_title),
                onBackNavigation = onBackNavigation
            )

            BigCard(
                showBack = uiState.form?.get("showBack") ?: "false",
                cardNumber = formattedCardNumber ?: "",
                cardHolderName = uiState.form?.get("cardHolderName") ?: "",
                expiryDate = formatDate(uiState.form?.get("expiryMonth") ?: "", uiState.form?.get("expiryYear") ?: ""),
                cvv = uiState.form?.get("cvv") ?: ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            AddCardCard(
                cardNumber = uiState.form?.get("cardNumber") ?: "",
                cardHolderName = uiState.form?.get("cardHolderName") ?: "",
                expiryMonth = uiState.form?.get("expiryMonth") ?: "",
                expiryYear = uiState.form?.get("expiryYear") ?: "",
                cvv = uiState.form?.get("cvv") ?: "",
                cardType = uiState.form?.get("cardType") ?: "",
                onValueChange = { key, value -> viewModel.setFormValue(key, value) },
                onAddCard = {
                    try {
                        viewModel.addCard(
                            Card(
                                number = uiState.form?.get("cardNumber") ?: "",
                                fullName = uiState.form?.get("cardHolderName") ?: "",
                                expirationDate = formatDate(uiState.form?.get("expiryMonth") ?: "", uiState.form?.get("expiryYear") ?: ""),
                                cvv = uiState.form?.get("cvv") ?: "",
                                type = when(uiState.form?.get("cardType") ?: "") { "CREDIT" -> CardType.CREDIT else -> CardType.DEBIT },
                            ), onAddCardSuccess
                        )
                    } catch(e: Exception) {
                        viewModel.setError(Error(400, e.message.toString()))
                    }
                }
            )
        }

}