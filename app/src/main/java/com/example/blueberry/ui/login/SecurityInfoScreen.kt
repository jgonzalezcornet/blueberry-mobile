package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenTitle

@Composable
fun SecurityInfoScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(R.string.security_info),
            onBackNavigation = onBackNavigation
        )
    }
}

@PreviewScreenSizes
@Composable
fun SecurityInfoScreenPreview() {
    SecurityInfoScreen()
}

