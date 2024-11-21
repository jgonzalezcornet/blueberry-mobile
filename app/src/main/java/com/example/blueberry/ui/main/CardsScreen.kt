package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.data.model.Card
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.cards.CardListCard
import com.example.blueberry.ui.components.cards.EliminateCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onAddCardClick: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
        LaunchedEffect(Unit) {
            viewModel.getCards()
        }

        var selectedCard by rememberSaveable { mutableStateOf<Card?>(null) }
        val uiState = viewModel.uiState

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding())
        ) {
            ScreenTitle(
                title = stringResource(R.string.cards_title),
                onBackNavigation = onBackNavigation
            )

            CardListCard(
                cards = uiState.cards,
                onCardClick = { card ->
                    selectedCard = card
                }
            )

            selectedCard?.let { card ->
                EliminateCard(
                    cardItem = card,
                    onClose = { selectedCard = null },
                    onDelete = { cardToDelete ->
                        viewModel.deleteCard(cardToDelete.id!!)
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
                        containerColor = MaterialTheme.colorScheme.primary
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

