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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

        if(!uiState.isAuthenticated && !uiState.isFetching){
            onUnauthenticated()
        }

        var cardNumber by remember { mutableStateOf("") }
        var cardHolderName by remember { mutableStateOf("") }
        var expiryMonth by remember { mutableStateOf("") }
        var expiryYear by remember { mutableStateOf("") }
        var cvv by remember { mutableStateOf("") }
        var cardType by remember { mutableStateOf(CardType.CREDIT) }
        var showBack by remember { mutableStateOf(false) }

        val formattedCardNumber = cardNumber.chunked(4).joinToString(" ")
        val formattedExpiryDate = formatDate(expiryMonth, expiryYear)

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
                showBack = showBack,
                cardNumber = formattedCardNumber,
                cardHolderName = cardHolderName,
                expiryDate = formattedExpiryDate,
                cvv = cvv
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.card_type_label),
                            color = Color.Gray
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Débito",
                                color = if (cardType == CardType.DEBIT) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                            Switch(
                                checked = cardType == CardType.CREDIT,
                                onCheckedChange = { isCredit ->
                                    cardType = if (isCredit) CardType.CREDIT else CardType.DEBIT
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                                    uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                                    uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                            Text(
                                text = "Crédito",
                                color = if (cardType == CardType.CREDIT) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }
                    }

                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = {
                            if (it.length <= 16 && it.all { char -> char.isDigit() }) {
                                cardNumber = it
                            }
                        },
                        label = { Text(stringResource(R.string.card_number_label)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = cardHolderName,
                        onValueChange = {
                            if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                                cardHolderName = it.uppercase()
                            }
                        },
                        label = { Text(stringResource(R.string.card_holder_name_label)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = expiryMonth,
                            onValueChange = {
                                if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                                    expiryMonth = it
                                }
                            },
                            label = { Text("MM") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = expiryYear,
                            onValueChange = {
                                if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                                    expiryYear = it
                                }
                            },
                            label = { Text("AA") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    OutlinedTextField(
                        value = cvv,
                        onValueChange = {
                            if (it.length <= 3 && it.all { char -> char.isDigit() }) {
                                cvv = it
                            }
                        },
                        label = { Text(stringResource(R.string.cvv_label)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState ->
                                showBack = focusState.isFocused
                            },
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            try {
                                viewModel.addCard(
                                    Card(
                                        null,
                                        cardNumber,
                                        formattedExpiryDate,
                                        cardHolderName,
                                        cvv,
                                        cardType,
                                        null,
                                        null
                                    ),
                                    onAddCardSuccess
                                )
                            } catch(e: Exception) {
                                viewModel.setError(Error(400, e.message.toString()))
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        enabled = cardNumber.length == 16 &&
                                cardHolderName.isNotEmpty() &&
                                expiryMonth.length == 2 &&
                                expiryYear.length == 2 &&
                                cvv.length == 3
                    ) {
                        Text(
                            text = stringResource(R.string.add_card_button),
                            color = Color.White
                        )
                    }
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun AddCardScreenPreview() {
    AddCardScreen()
}