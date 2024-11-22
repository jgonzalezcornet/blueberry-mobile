package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.components.home.BalanceCard
import com.example.blueberry.ui.components.home.CardListHome
import com.example.blueberry.ui.components.home.HomeButtons
import com.example.blueberry.ui.components.home.LastActivityCard
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onInsertMoneyClick: () -> Unit = {},
    onTransferMoneyClick: () -> Unit = {},
    onGoToProfileClick: () -> Unit = {},
    onActivityClick: () -> Unit = {},
    onCardsClick: () -> Unit = {},
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_home") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            initialized = true
            viewModel.initializeForm()
            viewModel.setFormValue("balanceVisible", "false")
        }
    }

    if(!uiState.isAuthenticated && !uiState.isFetching){
        onUnauthenticated()
    }

    LaunchedEffect(uiState.isAuthenticated, uiState.isFetching) {
        if(!uiState.isFetching && uiState.isAuthenticated) {
            viewModel.getCards()
            viewModel.getWalletDetails()
            viewModel.getCurrentUser()
            viewModel.getPayments()
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = getPadding())
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            )
    ) {
        Spacer(Modifier.width(8.dp))
        BalanceCard(
            balance = uiState.details?.balance.toString(),
            balanceVisible = uiState.form?.get("balanceVisible") ?: "false",
            onChangeBalanceVisibility = { value -> viewModel.setFormValue("balanceVisible", value) }
        )
        HomeButtons(
            onInsertMoneyClick = onInsertMoneyClick,
            onTransferMoneyClick = onTransferMoneyClick,
            onGoToProfileClick = onGoToProfileClick
        )
        LastActivityCard(
            activity = uiState.activities?.firstOrNull(),
            currentUser = uiState.currentUser,
            onClick = onActivityClick,
        )
        CardListHome(
            cards = uiState.cards,
            onClick = onCardsClick
        )
    }
}