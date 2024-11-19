package com.example.blueberry.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blueberry.ui.AppDestinations
import com.example.blueberry.ui.screens.ActivityScreen
import com.example.blueberry.ui.screens.CardsScreen
import com.example.blueberry.ui.screens.HomeScreen
import com.example.blueberry.ui.screens.ProfileScreen
import com.example.blueberry.ui.screens.SettingsScreen
import com.example.blueberry.ui.theme.BlueberryTheme


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.HOME.route
) {
    BlueberryTheme {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable(AppDestinations.HOME.route) { HomeScreen() }
            composable(AppDestinations.ACTIVITY.route) { ActivityScreen() }
            composable(AppDestinations.CARDS.route) { CardsScreen() }
            composable(AppDestinations.SETTINGS.route) { SettingsScreen() }
            composable("profile") { ProfileScreen() } // TODO: Ver como cambiar este path hardcodaeado
        }
    }
}
