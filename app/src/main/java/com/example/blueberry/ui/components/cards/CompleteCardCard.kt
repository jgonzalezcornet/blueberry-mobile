package com.example.blueberry.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R

@Composable
fun CompleteCardCard(
    modifier: Modifier = Modifier,
    cardItem: CardItem,
    showFullNumber: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardItem.cardType.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (cardItem.cardType.logo != 0) {
                Icon(
                    painter = painterResource(cardItem.cardType.logo),
                    contentDescription = stringResource(R.string.card_logo_description),
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.End)
                )
            }

            Text(
                text = if (showFullNumber) {
                    cardItem.cardNumber.chunked(4).joinToString(" ")
                } else {
                    "**** **** **** ${cardItem.lastFourDigits}"
                },
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.card_holder_label),
                        style = TextStyle(
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = cardItem.cardHolderName.uppercase(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = stringResource(R.string.card_expiry_label),
                        style = TextStyle(
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "${cardItem.expiryMonth}/${cardItem.expiryYear}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompleteCardCardPreview() {
    CompleteCardCard(
        cardItem = CardItem(
            cardNumber = "4111111111111111",
            cardHolderName = "Manuel Ahumada",
            expiryMonth = "12",
            expiryYear = "24",
            cvv = "123"
        ),
        showFullNumber = false
    )
}

@Preview(showBackground = true)
@Composable
fun CompleteCardCardFullNumberPreview() {
    CompleteCardCard(
        cardItem = CardItem(
            cardNumber = "4111111111111111",
            cardHolderName = "Manuel Ahumada",
            expiryMonth = "12",
            expiryYear = "24",
            cvv = "123"
        ),
        showFullNumber = true
    )
}

