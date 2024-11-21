package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenTitle
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueberry.ui.components.cards.BigCard


@Composable
fun AddCardScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onAddCardSuccess: () -> Unit = {}
) {
    var cardNumber by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var expiryMonth by remember { mutableStateOf("") }
    var expiryYear by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var showBack by remember { mutableStateOf(false) }

    val formattedCardNumber = cardNumber.chunked(4).joinToString(" ")
    val formattedExpiryDate = if (expiryMonth.isNotEmpty() || expiryYear.isNotEmpty()) {
        "${expiryMonth.take(2)}/${expiryYear.take(2)}"
    } else ""

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
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
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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
                    onClick = onAddCardSuccess,
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
