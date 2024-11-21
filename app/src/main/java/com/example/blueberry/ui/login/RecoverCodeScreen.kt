package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.PaddedContent
import com.example.blueberry.ui.components.home.RecoverPasswordCodeCard

@Composable
fun RecoverCodeScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onRecoverCodeSuccess: () -> Unit = {}
) {
    PaddedContent {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecoverPasswordCodeCard(
                onCancel = onCancel,
                onRecoverCodeSuccess = onRecoverCodeSuccess
            )
        }
    }
}


@PreviewScreenSizes
@Composable
fun RecoverCodeScreenPreview() {
    RecoverCodeScreen()
}