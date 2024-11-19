package com.example.blueberry.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenContent

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier
) {
    ScreenContent(
        title = stringResource(R.string.activity_title),
        description = stringResource(R.string.activity_description),
        modifier = modifier
    )
}

@PreviewScreenSizes
@Composable
fun ActivityScreenPreview() {
    ActivityScreen()
}

