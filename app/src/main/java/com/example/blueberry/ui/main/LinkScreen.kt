package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.R
import com.example.blueberry.ui.components.LinkCard
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.blueberry.ui.components.LinkGeneratedCard

@Composable
fun LinkScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {}
) {
    var linkModalOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(R.string.link_screen_title),
            onBackNavigation = onBackNavigation
        )
        LinkCard(
            onGenerateLink = { linkModalOpen = true }
        )
        if(linkModalOpen){
            LinkGeneratedCard(
                onClose = { linkModalOpen = false }
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun LinkScreenPreview() {
    LinkScreen()
}

