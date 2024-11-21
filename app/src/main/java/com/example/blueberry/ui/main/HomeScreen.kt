package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.ui.components.activity.ActivityItem
import com.example.blueberry.ui.components.activity.ActivityType
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
    onChargeMoneyClick: () -> Unit = {},
    onActivityClick: () -> Unit = {},
    onCardsClick: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    LaunchedEffect(Unit) {
        viewModel.getCards()
        viewModel.getWalletDetails()
    }

    val uiState = viewModel.uiState
    val activity = ActivityItem(java.sql.Date(2024 - 1900, 10, 19), ActivityType.SENT, "Nicolás Priotto", 3000.0)

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.width(8.dp))
        BalanceCard(
            balance = uiState.details?.balance.toString()
        )
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
            cards = uiState.cards,
            onClick = onCardsClick
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}