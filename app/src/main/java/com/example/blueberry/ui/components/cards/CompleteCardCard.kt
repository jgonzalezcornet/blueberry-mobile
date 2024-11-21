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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R
import com.example.blueberry.data.model.Card

@Composable
fun CompleteCardCard(
    modifier: Modifier = Modifier,
    cardItem: Card,
    showFullNumber: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardItem.brand.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (cardItem.brand.logo != 0) {
                Icon(
                    painter = painterResource(cardItem.brand.logo),
                    contentDescription = stringResource(R.string.card_logo_description),
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.End)
                )
            }

            Text(
                text = if (showFullNumber) {
                    cardItem.number.chunked(4).joinToString(" ")
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
                        text = cardItem.fullName.uppercase(),
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
                        text = cardItem.expirationDate,
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



