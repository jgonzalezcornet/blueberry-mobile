package com.example.blueberry.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.AppDestinations

@Composable
fun BottomBar(navController: NavHostController) {
    BottomNavigation {
        AppDestinations.entries.forEach { destination ->
            BottomNavigationItem(
                icon = { Icon(destination.icon, contentDescription = null) },
                label = { Text(stringResource(id = destination.label)) }, // This is allowed
                selected = navController.currentBackStackEntry?.destination?.route == destination.route,
                onClick = {
                    navController.navigate(destination.route) { // Use pre-resolved route strings
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}
