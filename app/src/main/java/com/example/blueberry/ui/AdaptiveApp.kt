package com.example.blueberry.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import com.example.blueberry.ui.components.TopBar
import com.example.blueberry.ui.login.LoginScreen
import com.example.blueberry.ui.login.RecoverCodeScreen
import com.example.blueberry.ui.login.RecoverScreen
import com.example.blueberry.ui.login.RegisterScreen
import com.example.blueberry.ui.login.SecurityInfoScreen
import com.example.blueberry.ui.login.TermsScreen
import com.example.blueberry.ui.login.ValidateScreen
import com.example.blueberry.ui.main.ActivityScreen
import com.example.blueberry.ui.main.AddCardScreen
import com.example.blueberry.ui.main.AliasScreen
import com.example.blueberry.ui.main.CardsScreen
import com.example.blueberry.ui.main.HomeScreen
import com.example.blueberry.ui.main.LinkScreen
import com.example.blueberry.ui.main.ProfileScreen
import com.example.blueberry.ui.main.TransferScreen
import com.example.blueberry.ui.navigation.AppDestinations
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme



@Composable
fun AdaptiveApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME ) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    IconButton(
                        onClick = { 
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = stringResource(R.string.close_menu_button_description),
                            tint = Color.Black
                        )
                    }
                }

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Home,
                                contentDescription = stringResource(id = R.string.home_title),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.home_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.HOME,
                    onClick = {
                        currentDestination = AppDestinations.HOME
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.AccountBalance,
                                contentDescription = stringResource(id = R.string.transfer_title),
                                tint = Color.Black
                            ) 
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.transfer_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.TRANSFER,
                    onClick = {
                        currentDestination = AppDestinations.TRANSFER
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Paid,
                                contentDescription = stringResource(id = R.string.alias_title),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.alias_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.ALIAS,
                    onClick = {
                        currentDestination = AppDestinations.ALIAS
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.History,
                                contentDescription = stringResource(id = R.string.activity_title),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.activity_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.ACTIVITY,
                    onClick = {
                        currentDestination = AppDestinations.ACTIVITY
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.CreditCard,
                                contentDescription = stringResource(id = R.string.cards_title),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.cards_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.CARDS,
                    onClick = {
                        currentDestination = AppDestinations.CARDS
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Link,
                                contentDescription = stringResource(id = R.string.link_title),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.link_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.LINK,
                    onClick = {
                        currentDestination = AppDestinations.LINK
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Rounded.Person,
                                contentDescription = stringResource(id = R.string.profile_title),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = stringResource(R.string.profile_title), color = Color.Black)
                        }
                    },
                    selected = currentDestination == AppDestinations.PROFILE,
                    onClick = {
                        currentDestination = AppDestinations.PROFILE
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Transparent)
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        isUserLoggedIn = currentDestination !in listOf(AppDestinations.LOGIN, AppDestinations.REGISTER, AppDestinations.RECOVER, AppDestinations.RECOVER_CODE, AppDestinations.TERMS, AppDestinations.SECURITY),
                        openModalNavigation = { scope.launch { drawerState.open() } }
                    )
                },
                containerColor = Color.Transparent
            ) { paddingValues ->
                when(currentDestination) {
                    AppDestinations.LOGIN -> LoginScreen(
                        modifier = Modifier.padding(paddingValues),
                        onNavigateToRegister = {
                            currentDestination = AppDestinations.REGISTER
                        },
                        onLoginSuccess = {
                            currentDestination = AppDestinations.HOME
                        },
                        onForgotPassword = {
                            currentDestination = AppDestinations.RECOVER
                        }
                    )
                    AppDestinations.REGISTER -> RegisterScreen(
                        modifier = Modifier.padding(paddingValues),
                        onNavigateBack = {
                            currentDestination = AppDestinations.LOGIN
                        },
                        onRegisterSuccess = {
                            currentDestination = AppDestinations.VALIDATE
                        }
                    )
                    AppDestinations.RECOVER -> RecoverScreen(
                        modifier = Modifier.padding(paddingValues),
                        onCancel =  { currentDestination = AppDestinations.LOGIN },
                        onRecoverSuccess = { currentDestination = AppDestinations.RECOVER_CODE }
                    )
                    AppDestinations.RECOVER_CODE -> RecoverCodeScreen(
                        modifier = Modifier.padding(paddingValues),
                        onCancel =  { currentDestination = AppDestinations.LOGIN },
                        onRecoverCodeSuccess = { currentDestination = AppDestinations.LOGIN }
                    )

                    AppDestinations.TERMS -> TermsScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.LOGIN }
                    )

                    AppDestinations.SECURITY -> SecurityInfoScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.LOGIN }
                    )

                    AppDestinations.VALIDATE -> ValidateScreen(
                        modifier = Modifier.padding(paddingValues),
                        onCancel =  { currentDestination = AppDestinations.REGISTER },
                        onValidateSuccess = { currentDestination = AppDestinations.LOGIN }
                    )

                    AppDestinations.HOME -> HomeScreen(
                        modifier = Modifier.padding(paddingValues),
                        onInsertMoneyClick = { currentDestination = AppDestinations.ALIAS },
                        onChargeMoneyClick = { currentDestination = AppDestinations.LINK },
                        onTransferMoneyClick = { currentDestination = AppDestinations.TRANSFER },
                        onActivityClick = { currentDestination = AppDestinations.ACTIVITY },
                        onCardsClick = { currentDestination = AppDestinations.CARDS }
                    )

                    AppDestinations.PROFILE -> ProfileScreen(
                        modifier = Modifier.padding(paddingValues),
                        onLogout = { currentDestination = AppDestinations.LOGIN },
                        onBackNavigation = { currentDestination = AppDestinations.HOME }
                    )
                    AppDestinations.ACTIVITY -> ActivityScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.HOME }
                    )
                    AppDestinations.CARDS -> CardsScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.HOME },
                        onAddCardClick = {
                            currentDestination = AppDestinations.ADD_CARD
                        }
                    )
                    AppDestinations.ADD_CARD -> AddCardScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = {
                            currentDestination = AppDestinations.CARDS
                        },
                        onAddCardSuccess = {
                            currentDestination = AppDestinations.CARDS
                        }
                    )
                    AppDestinations.ALIAS -> AliasScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.HOME }
                    )
                    AppDestinations.LINK -> LinkScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.HOME }
                    )
                    AppDestinations.TRANSFER -> TransferScreen(
                        modifier = Modifier.padding(paddingValues),
                        onBackNavigation = { currentDestination = AppDestinations.HOME },
                        onTransferSuccess = { currentDestination = AppDestinations.HOME }
                    )
                }
            }
        }
    }
}