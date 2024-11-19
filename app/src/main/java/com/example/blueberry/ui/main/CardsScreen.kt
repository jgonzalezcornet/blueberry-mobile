package com.example.blueberry.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenContent

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier
) {
    ScreenContent(
        title = stringResource(R.string.cards_title),
        description = stringResource(R.string.cards_description),
        modifier = modifier
    )
}

@PreviewScreenSizes
@Composable
fun CardsScreenPreview() {
    CardsScreen()
}

