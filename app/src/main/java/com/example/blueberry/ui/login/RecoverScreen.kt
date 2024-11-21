package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.RecoverCard

@Composable
fun RecoverScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onRecoverSuccess: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecoverCard(
            onCancel = onCancel,
            onRecoverSuccess = onRecoverSuccess
        )
    }
}


@PreviewScreenSizes
@Composable
fun RecoverScreenPreview() {
    RecoverScreen()
}