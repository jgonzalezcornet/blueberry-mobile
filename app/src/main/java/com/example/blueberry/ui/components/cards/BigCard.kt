package com.example.blueberry.ui.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R
import com.example.blueberry.data.model.CardBrand

@Composable
fun BigCard(
    showBack: Boolean,
    cardNumber: String,
    cardHolderName: String,
    expiryDate: String,
    cvv: String,
    modifier: Modifier = Modifier
) {
    val cardType = CardBrand.Companion.fromCardNumber(cardNumber)

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .graphicsLayer {
                rotationY = if (showBack) 180f else 0f
                cameraDistance = 12f * density
            },
        colors = CardDefaults.cardColors(containerColor = cardType.backgroundColor)
    ) {
        if (showBack) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { 
                        rotationY = 180f 
                    }
                    .padding(20.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = stringResource(R.string.cvv_label),
                        style = TextStyle(
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                    )
                    Text(
                        text = cvv.ifEmpty { "***" },
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(cardType.logo),
                    contentDescription = stringResource(R.string.card_logo_description),
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.End)
                )

                Text(
                    text = if (cardNumber.isEmpty()) "**** **** **** ****" else cardNumber,
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
                            text = cardHolderName.uppercase().ifEmpty { "NOMBRE APELLIDO" },
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
                            text = expiryDate.ifEmpty { "MM/YY" },
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
}

@Preview(showBackground = true)
@Composable
fun BigCardPreview() {
    BigCard(
        showBack = false,
        cardNumber = "4111111111111111",
        cardHolderName = "Manuel Ahumada",
        expiryDate = "12/24",
        cvv = "123"
    )
}

@Preview(showBackground = true)
@Composable
fun BigCardBackPreview() {
    BigCard(
        showBack = true,
        cardNumber = "4111111111111111",
        cardHolderName = "Manuel Ahumada",
        expiryDate = "12/24",
        cvv = "123"
    )
}
