package com.example.blueberry.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.blueberry.R

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home, Icons.Default.Home, "home"),
    ACTIVITY(R.string.activity, Icons.Default.Favorite, "activity"),
    CARDS(R.string.cards, Icons.Default.ShoppingCart, "cards"),
    SETTINGS(R.string.settings, Icons.Default.Settings, "settings"),
}
