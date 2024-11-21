package com.example.blueberry.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme

@Composable
fun TransferCard(
    modifier: Modifier = Modifier,
    onTransferConfirmed: (type: String, destination: String, amount: String) -> Unit = { _, _, _ -> }
)  {
    var selectedOption by remember { mutableStateOf("Alias") }
    var amount by remember { mutableStateOf("") }
    var destinationInput by remember { mutableStateOf("") }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.account_type),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { selectedOption = "Alias" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "Alias") MaterialTheme.colorScheme.primary else Color.Gray
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.alias),
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                Button(
                    onClick = { selectedOption = "CVU/CBU" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "CVU/CBU") MaterialTheme.colorScheme.primary else Color.Gray
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.cvu_cbu),
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                Button(
                    onClick = { selectedOption = "Link de pago" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "Link de pago") MaterialTheme.colorScheme.primary else Color.Gray
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.payment_link),
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            OutlinedTextField(
                value = destinationInput,
                onValueChange = { destinationInput = it },
                label = { 
                    Text(
                        when (selectedOption) {
                            "Alias" -> stringResource(R.string.enter_alias_title)
                            "CVU/CBU" -> stringResource(R.string.enter_cvu_title)
                            else -> stringResource(R.string.enter_payment_link_title)
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text(stringResource(id = R.string.amount_label)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = "Monto",
                        tint = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                singleLine = true
            )

            Button(
                onClick = { 
                    if (amount.isNotEmpty() && destinationInput.isNotEmpty()) {
                        onTransferConfirmed(selectedOption, destinationInput, amount)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = amount.isNotEmpty() && destinationInput.isNotEmpty()
            ) {
                Text(
                    text = stringResource(id = R.string.continue_button),
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferCardPreview() {
    TransferCard()
}