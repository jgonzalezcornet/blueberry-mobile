package com.example.blueberry.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme

@Composable
fun EliminateCard(
    cardItem: CardItem,
    onClose: () -> Unit = {},
    onDelete: (CardItem) -> Unit = {}
) {
    Dialog(onDismissRequest = { onClose() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_button),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClose() },
                        tint = Color.Gray
                    )
                }

                CompleteCardCard(
                    cardItem = cardItem,
                    showFullNumber = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onDelete(cardItem) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.delete_card_button),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EliminateCardPreview() {
    EliminateCard(
        cardItem = CardItem(
            cardNumber = "4111111111111111",
            cardHolderName = "Manuel Ahumada",
            expiryMonth = "12",
            expiryYear = "24",
            cvv = "123"
        )
    )
}

