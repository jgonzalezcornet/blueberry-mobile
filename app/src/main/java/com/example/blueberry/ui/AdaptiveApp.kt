package com.example.blueberry.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import com.example.blueberry.ui.components.TopBar
import com.example.blueberry.ui.components.isTablet
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
import com.example.blueberry.ui.main.ProfileScreen
import com.example.blueberry.ui.main.TransferScreen
import com.example.blueberry.ui.navigation.AppDestinations
import kotlinx.coroutines.launch
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.TextButton
import java.util.Stack
import androidx.compose.runtime.LaunchedEffect
import com.example.blueberry.ui.components.ErrorHandler

@Composable
fun DrawerItems(
    currentDestination: AppDestinations,
    items: List<Triple<androidx.compose.ui.graphics.vector.ImageVector, Int, AppDestinations>> = emptyList(),
    onDestinationSelected: (AppDestinations) -> Unit
) {
    items.forEach { (icon, labelResId, destination) ->
        NavigationDrawerItem(
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.White,
                selectedContainerColor = MaterialTheme.colorScheme.secondary,
            ),
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = stringResource(labelResId), tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(labelResId), color = Color.Black)
                }
            },
            selected = currentDestination == destination,
            onClick = { onDestinationSelected(destination) }
        )
    }
}

@Composable
fun RailItems(
    currentDestination: AppDestinations,
    items: List<Triple<androidx.compose.ui.graphics.vector.ImageVector, Int, AppDestinations>> = emptyList(),
    onDestinationSelected: (AppDestinations) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .width(120.dp)
    ) {
        items(items.size) { index ->
            val (icon, labelResId, destination) = items[index]
            NavigationRailItem(
                icon = { Icon(icon, contentDescription = stringResource(labelResId)) },
                label = {
                    Text(
                        text = stringResource(labelResId),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        maxLines = 5,
                        softWrap = true,
                        textAlign = TextAlign.Center
                    )
                },
                selected = currentDestination == destination,
                onClick = { onDestinationSelected(destination) },
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }
}


@Composable
fun AppContent(
    currentDestination: MutableState<AppDestinations>,
    paddingValues: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues()
) {
    ErrorHandler()

    when (currentDestination.value) {
        AppDestinations.LOGIN -> LoginScreen(
            modifier = Modifier.padding(paddingValues),
            onNavigateToRegister = { currentDestination.value = AppDestinations.REGISTER },
            onLoginSuccess = { currentDestination.value = AppDestinations.HOME },
            onForgotPassword = { currentDestination.value = AppDestinations.RECOVER },
            onNavigateToTerms = { currentDestination.value  = AppDestinations.TERMS },
            onNavigateToSecurityInfo = { currentDestination.value = AppDestinations.SECURITY }
        )
        AppDestinations.REGISTER -> RegisterScreen(
            modifier = Modifier.padding(paddingValues),
            onNavigateBack = { currentDestination.value = AppDestinations.LOGIN },
            onRegisterSuccess = { currentDestination.value = AppDestinations.VALIDATE },
            onNavigateToTerms = { currentDestination.value  = AppDestinations.TERMS },
            onNavigateToSecurityInfo = { currentDestination.value = AppDestinations.SECURITY }
        )
        AppDestinations.VALIDATE -> ValidateScreen(
            modifier = Modifier.padding(paddingValues),
            onCancel =  { currentDestination.value = AppDestinations.REGISTER },
            onValidateSuccess = { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.RECOVER -> RecoverScreen(
            modifier = Modifier.padding(paddingValues),
            onCancel = { currentDestination.value = AppDestinations.LOGIN },
            onRecoverSuccess = { currentDestination.value = AppDestinations.RECOVER_CODE }
        )
        AppDestinations.RECOVER_CODE -> RecoverCodeScreen(
            modifier = Modifier.padding(paddingValues),
            onCancel = { currentDestination.value = AppDestinations.LOGIN },
            onRecoverCodeSuccess = { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.TERMS -> TermsScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.SECURITY -> SecurityInfoScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.HOME -> HomeScreen(
            modifier = Modifier.padding(paddingValues),
            onInsertMoneyClick = { currentDestination.value = AppDestinations.ALIAS },
            onTransferMoneyClick = { currentDestination.value = AppDestinations.TRANSFER },
            onGoToProfileClick = { currentDestination.value = AppDestinations.PROFILE },
            onActivityClick = { currentDestination.value = AppDestinations.ACTIVITY },
            onCardsClick = { currentDestination.value = AppDestinations.CARDS },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.PROFILE -> ProfileScreen(
            modifier = Modifier.padding(paddingValues),
            onLogout = { currentDestination.value = AppDestinations.LOGIN },
            onBackNavigation = { currentDestination.value = AppDestinations.HOME },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.ACTIVITY -> ActivityScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.HOME },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.CARDS -> CardsScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.HOME },
            onAddCardClick = { currentDestination.value = AppDestinations.ADD_CARD },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.ADD_CARD -> AddCardScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.CARDS },
            onAddCardSuccess = { currentDestination.value = AppDestinations.CARDS },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.ALIAS -> AliasScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.HOME },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
        AppDestinations.TRANSFER -> TransferScreen(
            modifier = Modifier.padding(paddingValues),
            onBackNavigation = { currentDestination.value = AppDestinations.HOME },
            onUnauthenticated =  { currentDestination.value = AppDestinations.LOGIN }
        )
    }
}

@Composable
fun AdaptiveApp() {
    val currentDestination = rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val routeStack = rememberSaveable { Stack<AppDestinations>() }

    LaunchedEffect(Unit) {
        routeStack.push(currentDestination.value)
    }

    LaunchedEffect(currentDestination.value) {
        if(currentDestination.value == AppDestinations.HOME && routeStack.peek() == AppDestinations.LOGIN){
            routeStack.clear()
            routeStack.push(currentDestination.value)
        }
        routeStack.push(currentDestination.value)
    }

    BackHandler(enabled = routeStack.size > 1) {
        routeStack.pop()
        currentDestination.value = routeStack.peek()
        routeStack.pop()
    }

    val items = listOf(
        Triple(Icons.Rounded.Home, R.string.home_title, AppDestinations.HOME),
        Triple(Icons.Rounded.AccountBalance, R.string.transfer_title, AppDestinations.TRANSFER),
        Triple(Icons.Rounded.Paid, R.string.alias_title, AppDestinations.ALIAS),
        Triple(Icons.Rounded.History, R.string.activity_title, AppDestinations.ACTIVITY),
        Triple(Icons.Rounded.CreditCard, R.string.cards_title, AppDestinations.CARDS),
        Triple(Icons.Rounded.Person, R.string.profile_title, AppDestinations.PROFILE)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Scaffold(
            topBar = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    TopBar(
                        isUserLoggedIn = currentDestination.value !in listOf(
                            AppDestinations.LOGIN,
                            AppDestinations.REGISTER,
                            AppDestinations.RECOVER,
                            AppDestinations.RECOVER_CODE,
                            AppDestinations.TERMS,
                            AppDestinations.SECURITY
                        ),
                        openModalNavigation = { scope.launch { drawerState.open() } },

                        )
                }
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
                if (isTablet() && currentDestination.value !in listOf(
                        AppDestinations.LOGIN,
                        AppDestinations.REGISTER,
                        AppDestinations.RECOVER,
                        AppDestinations.RECOVER_CODE,
                        AppDestinations.TERMS,
                        AppDestinations.SECURITY
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        NavigationRail(
                            modifier = Modifier
                                .fillMaxHeight(),
                            containerColor = Color.White
                        ) {
                            RailItems(
                                currentDestination = currentDestination.value,
                                items = items,
                                onDestinationSelected = { currentDestination.value = it }
                            )
                        }

                        AppContent(currentDestination)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                } else {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet(
                                drawerContainerColor = Color.White
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    IconButton(
                                        onClick = { scope.launch { drawerState.close() } },
                                        modifier = Modifier.align(Alignment.TopEnd)
                                    ) {
                                        Icon(
                                            Icons.Rounded.Close,
                                            contentDescription = stringResource(R.string.close_menu_button_description),
                                            tint = Color.Black
                                        )
                                    }
                                }

                                DrawerItems(
                                    currentDestination = currentDestination.value,
                                    items = items,
                                    onDestinationSelected = {
                                        currentDestination.value = it
                                        scope.launch { drawerState.close() }
                                    }
                                )
                            }
                        }
                    ) {
                        AppContent(currentDestination, paddingValues)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

}
