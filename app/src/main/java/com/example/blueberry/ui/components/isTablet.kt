package com.example.blueberry.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getPadding(): Dp {
    return (if (isTablet()) 128.dp else 16.dp)
}
@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val orientation = configuration.orientation

    return screenWidthDp >= 600 || // Tablet based on width
            (screenWidthDp >= 480 && orientation == Configuration.ORIENTATION_LANDSCAPE) // Horizontal phone
}