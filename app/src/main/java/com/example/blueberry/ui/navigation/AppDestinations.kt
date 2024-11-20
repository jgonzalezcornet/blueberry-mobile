package com.example.blueberry.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Create
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.blueberry.R

enum class AppDestinations (
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val description: Int
){
    HOME(R.string.home_title, Icons.Rounded.AccountCircle, R.string.home_title),
    PROFILE(R.string.profile_title, Icons.Rounded.AccountCircle, R.string.profile_title),
    ACTIVITY(R.string.activity_title, Icons.Rounded.AccountCircle, R.string.activity_title),
    CARDS(R.string.cards_title, Icons.Rounded.AccountCircle, R.string.cards_title),
    ADD_CARD(R.string.add_card_title, Icons.Rounded.AccountCircle, R.string.add_card_title),
    ALIAS(R.string.alias_title, Icons.Rounded.AccountCircle, R.string.alias_title),
    LINK(R.string.link_title, Icons.Rounded.AccountCircle, R.string.link_title),
    TRANSFER(R.string.transfer_title, Icons.Rounded.AccountCircle, R.string.transfer_title),
    LOGIN(R.string.login_title, Icons.Rounded.AccountCircle, R.string.login_title),
    REGISTER(R.string.register_title, Icons.Rounded.Create, R.string.register_title)
}
