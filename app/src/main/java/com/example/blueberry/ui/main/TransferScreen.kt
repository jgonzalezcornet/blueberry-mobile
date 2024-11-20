package com.example.blueberry.ui.main

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.blueberry.ui.components.PayTransferCard
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.TransferCard
import com.example.blueberry.ui.components.cards.CardItem

@Composable
fun TransferScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {}
) {
    var transferStage by remember { mutableStateOf(0) }
    var transferType by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().background(Color.Transparent)
    ) {
        if(transferStage == 0){
            ScreenTitle(
                title = stringResource(R.string.transfer_title),
                onBackNavigation = onBackNavigation
            )

            TransferCard(
                onTransferConfirmed = { type, dest, amt -> 
                    transferType = type
                    destination = dest
                    amount = amt
                    transferStage = 1
                }
            )
        } else if(transferStage == 1){
            PayTransferCard(
                onCancel = { transferStage = 0 },
                transferType = transferType,
                destination = destination,
                amount = amount.toIntOrNull() ?: 0,
                availableCards = listOf(
                    CardItem(
                        cardNumber = "4111111111111111",
                        cardHolderName = "Manuel Ahumada",
                        expiryMonth = "12",
                        expiryYear = "24",
                        cvv = "123"
                    )
                )
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun TransferScreenPreview() {
    TransferScreen()
}