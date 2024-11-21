package com.example.blueberry.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.blueberry.R

enum class AppDestinations (
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val description: Int
){
    HOME(R.string.home_title, Icons.Rounded.Home, R.string.home_title),
    PROFILE(R.string.profile_title, Icons.Rounded.Person, R.string.profile_title),
    ACTIVITY(R.string.activity_title, Icons.Rounded.History, R.string.activity_title),
    CARDS(R.string.cards_title, Icons.Rounded.CreditCard, R.string.cards_title),
    ADD_CARD(R.string.add_card_title, Icons.Rounded.CreditCard, R.string.add_card_title),
    ALIAS(R.string.alias_title, Icons.Rounded.Paid, R.string.alias_title),
    LINK(R.string.link_title, Icons.Rounded.Link, R.string.link_title),
    TRANSFER(R.string.transfer_title, Icons.Rounded.AccountBalance, R.string.transfer_title),
    LOGIN(R.string.login_title, Icons.Rounded.AccountCircle, R.string.login_title),
    REGISTER(R.string.register_title, Icons.Rounded.ManageAccounts, R.string.register_title),
    RECOVER(R.string.recover_title, Icons.Rounded.Create, R.string.recover_title),
    RECOVER_CODE(R.string.recover_password_code_title, Icons.Rounded.Album, R.string.recover_password_code_title),
    TERMS(R.string.terms_and_conditions, Icons.Rounded.AccountBalance, R.string.terms_and_conditions),
    SECURITY(R.string.security_info, Icons.Rounded.AccountBalance, R.string.security_info)
}
