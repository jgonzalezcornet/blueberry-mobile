package com.example.blueberry.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation // Corrected import
import androidx.compose.material.BottomNavigationItem // Corrected import
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.unit.dp
import com.example.blueberry.ui.AppDestinations

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppDestinations.HOME.route

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondary, // Secondary color for the bar
        elevation = 8.dp
    ) {
        AppDestinations.entries.forEach { destination ->
            val isSelected = destination.route == currentRoute

            BottomNavigationItem(
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = null,
                        tint = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(bottom = 4.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.label),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                selected = isSelected,
                alwaysShowLabel = true,
                // selectedContentColor = MaterialTheme.colorScheme.primary, // No need for this anymore
                // unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant, // No need for this anymore
                onClick = {
                    navController.navigate(destination.route) {
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