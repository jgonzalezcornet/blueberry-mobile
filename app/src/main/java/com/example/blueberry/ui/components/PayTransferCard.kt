package com.example.blueberry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R
import com.example.blueberry.ui.components.cards.CompleteCardCard
import androidx.compose.material3.MaterialTheme
import com.example.blueberry.data.model.Card

@Composable
fun PayTransferCard(
    modifier: Modifier = Modifier,
    destination: String,
    amount: Int,
    availableCards: List<Card>,
    onCancel: () -> Unit = {},
    onPay: () -> Unit,
    selectedPaymentMethod: String,
    onValueChange: (String, String) -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }

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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.payment_details),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.destination_label),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Email: $destination",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.amount_label),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "$ $amount",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Text(
                text = stringResource(R.string.select_payment_method),
                fontSize = 16.sp,
                color = Color.Gray
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                state = rememberLazyListState()
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(180.dp)
                            .clickable {
                                currentPage = 0
                                onValueChange("selectedPaymentMethod", "account_balance")
                                onValueChange("selectedCardId", "0")
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (currentPage == 0) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.account_balance),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                items(availableCards.size) { index ->
                    val card = availableCards[index]
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .clickable {
                                currentPage = index + 1
                                onValueChange("selectedCardId", card.id.toString())
                                onValueChange("selectedPaymentMethod", card.number)
                            }
                    ) {
                        CompleteCardCard(
                            cardItem = card,
                            showFullNumber = false
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(availableCards.size + 1) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = if (currentPage == index) MaterialTheme.colorScheme.primary else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Text(
                        text = stringResource(R.string.cancel_button),
                        color = Color.White
                    )
                }

                Button(
                    onClick = { 
                        onPay()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    enabled = selectedPaymentMethod != ""
                ) {
                    Text(
                        text = stringResource(R.string.pay_button),
                        color = Color.White
                    )
                }
            }
        }
    }
}


