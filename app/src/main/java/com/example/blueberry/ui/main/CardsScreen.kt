package com.example.blueberry.ui.main

import android.util.Log
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
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.blueberry.R
import com.example.blueberry.data.model.Card
import com.example.blueberry.data.model.Error
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
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_cards") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            Log.w("SKIBIDIIIIIII", initialized.toString())
            initialized = true
            viewModel.initializeForm()
            viewModel.setFormValue("selectedCard", "")
        }
    }
        
        if(!uiState.isAuthenticated && !uiState.isFetching){
            onUnauthenticated()
        }

        var updateTrigger by rememberSaveable { mutableIntStateOf(0) }

        LaunchedEffect(updateTrigger) {
            viewModel.getCards()
        }

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
                    viewModel.setFormValue("selectedCard", card.id.toString())
                }
            )

            if(uiState.form?.get("selectedCard") != ""){
                var card = uiState.cards?.find { it.id.toString() == uiState.form?.get("selectedCard") }
                if(card != null){
                    EliminateCard(
                        cardItem = card,
                        onClose = { viewModel.setFormValue("selectedCard", "") },
                        onDelete = { cardToDelete ->
                            try {
                                viewModel.deleteCard(cardToDelete.id!!, {
                                    viewModel.setFormValue("selectedCard", "")
                                    updateTrigger += 1
                                })
                            } catch(e: Exception) {
                                viewModel.setError(Error(400, e.message.toString()))
                            }
                        }
                    )
                }
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