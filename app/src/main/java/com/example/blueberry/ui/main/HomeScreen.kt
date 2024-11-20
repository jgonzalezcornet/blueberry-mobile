package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueberry.ui.components.activity.ActivityItem
import com.example.blueberry.ui.components.activity.ActivityType
import com.example.blueberry.ui.components.cards.CardItem
import com.example.blueberry.ui.components.home.BalanceCard
import com.example.blueberry.ui.components.home.CardListHome
import com.example.blueberry.ui.components.home.HomeButtons
import com.example.blueberry.ui.components.home.LastActivityCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onInsertMoneyClick: () -> Unit = {},
    onTransferMoneyClick: () -> Unit = {},
    onChargeMoneyClick: () -> Unit = {},
    onActivityClick: () -> Unit = {},
    onCardsClick: () -> Unit = {}
) {
    // TESTEO
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
    val activity = ActivityItem(java.sql.Date(2024 - 1900, 10, 19), ActivityType.SENT, "Nicolás Priotto", 3000.0)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 80.dp, end = 16.dp, bottom = 4.dp)
    ) {
        Spacer(Modifier.width(8.dp))
        BalanceCard()
        HomeButtons(
            onInsertMoneyClick = onInsertMoneyClick,
            onTransferMoneyClick = onTransferMoneyClick,
            onChargeMoneyClick = onChargeMoneyClick
        )
        LastActivityCard(
            activity = activity,
            onClick = onActivityClick,
        )
        CardListHome(
            cards = sampleCards,
            onClick = onCardsClick
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

