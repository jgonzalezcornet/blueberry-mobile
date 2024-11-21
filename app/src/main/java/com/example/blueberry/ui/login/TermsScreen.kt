package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.getPadding

@Composable
fun TermsScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {}
) {
        Column(
            modifier = modifier
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding())
                .fillMaxSize()
        ) {
            ScreenTitle(
                title = stringResource(R.string.terms_and_conditions),
                onBackNavigation = onBackNavigation
            )
        }

}

@PreviewScreenSizes
@Composable
fun TermsScreenPreview() {
    TermsScreen()
}


