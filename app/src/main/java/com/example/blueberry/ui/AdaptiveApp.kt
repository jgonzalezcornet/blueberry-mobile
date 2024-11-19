package com.example.blueberry.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.blueberry.ui.components.TopBar
import com.example.blueberry.ui.login.LoginScreen
import com.example.blueberry.ui.login.RegisterScreen
import com.example.blueberry.ui.main.HomeScreen
import com.example.blueberry.ui.navigation.AppDestinations
import com.example.blueberry.R

@Composable
fun AdaptiveApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.LOGIN) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { 
                NavigationDrawerItem(
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Menu,
                                contentDescription = stringResource(id = R.string.hamburger_icon_description),
                                tint = Color.Black
                            ) 
                            Text(text = "Drawer Item") 
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                NavigationDrawerItem(
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Add,
                                contentDescription = stringResource(id = R.string.hamburger_icon_description),
                                tint = Color.Black
                            ) 
                            Text(text = "Drawer Item2") 
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )

                NavigationDrawerItem(
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Menu,
                                contentDescription = stringResource(id = R.string.hamburger_icon_description),
                                tint = Color.Black
                            ) 
                            Text(text = "Drawer Item") 
                        }
                    },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }
        },
    ) {


        Scaffold(
        topBar = {
            TopBar(
                isUserLoggedIn = currentDestination !in listOf(AppDestinations.LOGIN, AppDestinations.REGISTER)
            )
        }
    ) { paddingValues ->
        when(currentDestination) {
            AppDestinations.LOGIN -> LoginScreen(
                modifier = Modifier.padding(paddingValues),
                onNavigateToRegister = { 
                    currentDestination = AppDestinations.REGISTER 
                },
                onLoginSuccess = {
                    currentDestination = AppDestinations.HOME
                }
            )
            AppDestinations.REGISTER -> RegisterScreen(
                modifier = Modifier.padding(paddingValues),
                onNavigateBack = { 
                    currentDestination = AppDestinations.LOGIN 
                },
                onRegisterSuccess = {
                    currentDestination = AppDestinations.HOME
                }
            )
            
            AppDestinations.HOME -> HomeScreen(
                modifier = Modifier.padding(paddingValues)
            )

            AppDestinations.PROFILE -> TODO()
            AppDestinations.ACTIVITY -> TODO()
            AppDestinations.CARDS -> TODO()
            AppDestinations.ALIAS -> TODO()
            AppDestinations.LINK -> TODO()
            AppDestinations.TRANSFER -> TODO()
        }
    }


    }
}