package com.example.blueberry.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme

@Composable
fun TransferCard(
    modifier: Modifier = Modifier,
    onTransferConfirmed: (destination: String, amount: String, description: String) -> Unit = { _, _, _ -> }
)  {
    var amount by remember { mutableStateOf("") }
    var destinationInput by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
            OutlinedTextField(
                value = destinationInput,
                onValueChange = { destinationInput = it },
                label = { 
                    Text(
                        stringResource(R.string.transfer_email_label)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Correo electronico",
                        tint = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
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

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(id = R.string.description_label)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = "Descripcion",
                        tint = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                singleLine = true
            )

            Button(
                onClick = { 
                    if (amount.isNotEmpty() && destinationInput.isNotEmpty()) {
                        onTransferConfirmed(destinationInput, amount, description)
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