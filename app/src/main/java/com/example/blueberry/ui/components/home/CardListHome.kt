package com.example.blueberry.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueberry.ui.components.cards.CardCard
import com.example.blueberry.ui.components.cards.CardItem

@Composable
fun CardListHome(
    cards: List<CardItem>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(cards) { card ->
                CardCard(
                    cardItem = card,
                    onCardClick = onClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListCardPreview() {
    val sampleCards = listOf(
        CardItem(
            cardNumber = "4111111111111111",
            cardHolderName = "Manuel Ahumada",
            expiryMonth = "12",
            expiryYear = "24",
            cvv = "123"
        ),
        CardItem(
            cardNumber = "5105105105105100",
            cardHolderName = "Nicolas Priotto",
            expiryMonth = "06",
            expiryYear = "25",
            cvv = "456"
        ),
        CardItem(
            cardNumber = "371449635398431",
            cardHolderName = "Josefina Gonzalez",
            expiryMonth = "09",
            expiryYear = "26",
            cvv = "789"
        )
    )

    CardListHome(cards = sampleCards)
}