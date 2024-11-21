package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.components.home.RecoverPasswordCodeCard

@Composable
fun RecoverCodeScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onRecoverCodeSuccess: () -> Unit = {}
) {
        Column(
            modifier = modifier
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecoverPasswordCodeCard(
                onCancel = onCancel,
                onRecoverCodeSuccess = onRecoverCodeSuccess
            )
        }

}


@PreviewScreenSizes
@Composable
fun RecoverCodeScreenPreview() {
    RecoverCodeScreen()
}