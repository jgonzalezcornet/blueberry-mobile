package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.cards.CardItem
import com.example.blueberry.ui.components.cards.CardListCard
import com.example.blueberry.ui.components.cards.EliminateCard
import com.example.blueberry.ui.theme.PrimaryBlue

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onAddCardClick: () -> Unit = {}
) {
    var selectedCard by rememberSaveable { mutableStateOf<CardItem?>(null) }
    // Lista de ejemplo de tarjetas
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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(R.string.cards_title),
            onBackNavigation = onBackNavigation
        )
        
        CardListCard(
            cards = sampleCards,
            onCardClick = { card ->
                selectedCard = card
            }
        )

        selectedCard?.let { card ->
            EliminateCard(
                cardItem = card,
                onClose = { selectedCard = null },
                onDelete = { cardToDelete ->
                    // Aquí iría la lógica para eliminar la tarjeta
                    selectedCard = null
                }
            )
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = onAddCardClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Text(
                    text = stringResource(R.string.add_card_button),
                    color = Color.White
                )
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun CardsScreenPreview() {
    CardsScreen()
}

