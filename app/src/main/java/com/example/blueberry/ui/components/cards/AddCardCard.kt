package com.example.blueberry.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import com.example.blueberry.data.model.CardType

@Composable
fun AddCardCard (
    cardNumber: String,
    cardHolderName: String,
    expiryMonth: String,
    expiryYear: String,
    cvv: String,
    cardType: String,
    onValueChange: (String, String) -> Unit,
    onAddCard: () -> Unit,
) {
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
                        color = if (cardType == CardType.DEBIT.toString()) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                    Switch(
                        checked = cardType == CardType.CREDIT.toString(),
                        onCheckedChange = { isCredit ->
                            if (isCredit)
                                onValueChange("cardType", CardType.CREDIT.toString())
                            else
                                onValueChange("cardType", CardType.DEBIT.toString())
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
                        color = if (cardType ==  CardType.CREDIT.toString()) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                }
            }

            OutlinedTextField(
                value = cardNumber,
                onValueChange = {
                    if (it.length <= 16 && it.all { char -> char.isDigit() }) {
                        onValueChange("cardNumber", it)
                    }
                },
                label = {
                    Text(
                        stringResource(R.string.card_number_label),
                        color = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = cardHolderName,
                onValueChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                        onValueChange("cardHolderName", it.uppercase())
                    }
                },
                label = {
                    Text(
                        stringResource(R.string.card_holder_name_label),
                        color = Color.Gray
                    )
                },
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
                            onValueChange("expiryMonth", it)
                        }
                    },
                    label = {
                        Text(
                            text = "MM",
                            color = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                OutlinedTextField(
                    value = expiryYear,
                    onValueChange = {
                        if (it.length <= 2 && it.all { char -> char.isDigit() }) {
                            onValueChange("expiryYear", it)
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.year_input_cards),
                            color = Color.Gray
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            OutlinedTextField(
                value = cvv,
                onValueChange = {
                    if (it.length <= 4 && it.all { char -> char.isDigit() }) {
                        onValueChange("cvv", it)
                    }
                },
                label = {
                    Text(
                        stringResource(R.string.cvv_label),
                        color = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        onValueChange("showBack", focusState.isFocused.toString())
                    },
                singleLine = true
            )

            Button(
                onClick = onAddCard,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = cardNumber.length == 16 &&
                        cardHolderName.isNotEmpty() &&
                        expiryMonth.length == 2 &&
                        expiryYear.length == 2 &&
                        (cvv.length == 3 || cvv.length == 4)
            ) {
                Text(
                    text = stringResource(R.string.add_card_button),
                    color = Color.White
                )
            }
        }
    }
}