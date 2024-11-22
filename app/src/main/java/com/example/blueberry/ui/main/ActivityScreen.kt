package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.activity.ActivityListCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {},
    onUnauthenticated: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
        val uiState = viewModel.uiState

        if(!uiState.isAuthenticated && !uiState.isFetching){
            onUnauthenticated()
        }

        LaunchedEffect(Unit) {
            viewModel.getPayments()
            viewModel.getCurrentUser()
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = getPadding()),
        ) {
            ScreenTitle(
                title = stringResource(R.string.activity_title),
                onBackNavigation = onBackNavigation
            )
            ActivityListCard(uiState.activities,uiState.currentUser)
        }

}
