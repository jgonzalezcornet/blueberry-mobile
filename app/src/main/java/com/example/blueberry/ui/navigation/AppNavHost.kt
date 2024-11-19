package com.example.blueberry.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blueberry.ui.screens.ActivityScreen
import com.example.blueberry.ui.screens.CardsScreen
import com.example.blueberry.ui.screens.HomeScreen
import com.example.blueberry.ui.screens.ProfileScreen
import com.example.blueberry.ui.theme.BlueberryTheme


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    BlueberryTheme {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("activity") {
                ActivityScreen()
            }
            composable("cards") {
                CardsScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
        }
    }
}