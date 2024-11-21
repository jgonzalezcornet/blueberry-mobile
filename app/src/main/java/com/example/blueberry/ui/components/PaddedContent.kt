package com.example.blueberry.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun PaddedContent(content: @Composable () -> Unit) {
    val horizontalPadding = if (isTablet()) 128.dp else 16.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = horizontalPadding)
    ) {
        content()
    }
}

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val orientation = configuration.orientation

    return screenWidthDp >= 600 || // Tablet based on width
            (screenWidthDp >= 480 && orientation == Configuration.ORIENTATION_LANDSCAPE) // Horizontal phone
}