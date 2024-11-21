package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.ValidateCard
import com.example.blueberry.ui.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.blueberry.MyApplication

@Composable
fun ValidateScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onValidateSuccess: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ValidateCard(
            onCancel = onCancel,
            onValidate = { code ->
                viewModel.verify(code)
                onValidateSuccess()
            }
        )
    }
}

@PreviewScreenSizes
@Composable
fun ValidateScreenPreview() {
    ValidateScreen()
}

