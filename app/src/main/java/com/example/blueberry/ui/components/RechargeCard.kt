package com.example.blueberry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.blueberry.R
import com.example.blueberry.data.model.Card
import com.example.blueberry.ui.components.cards.CompleteCardCard

@Composable
fun RechargeCard(
    modifier: Modifier = Modifier,
    availableCards: List<Card>,
    onClose: () -> Unit = {},
    onConfirm: () -> Unit,
    amount: String,
    selectedCard: String,
    onValueChange: (String, String) -> Unit
) {

    var currentPage by remember { mutableStateOf(0) }

    Dialog(onDismissRequest = { onClose() }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.recharge_title),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_button),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClose() },
                        tint = Color.Gray
                    )
                }

                OutlinedTextField(
                    value = amount,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() })
                            onValueChange("amount", it)
                    },
                    label = { Text(stringResource(R.string.recharge_amount_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Text(
                    text = stringResource(R.string.select_card_label),
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    state = rememberLazyListState()
                ) {
                    items(availableCards.size) { index ->
                        val card = availableCards[index]
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .clickable {
                                    currentPage = index
                                    onValueChange("selectedCard", card.id.toString())
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
                    repeat(availableCards.size) { index ->
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

                Button(
                    onClick = onConfirm,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    enabled = amount.isNotEmpty() && selectedCard != ""
                ) {
                    Text(
                        text = stringResource(R.string.recharge_confirm_button),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

