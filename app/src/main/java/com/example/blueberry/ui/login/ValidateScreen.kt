package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.PaddedContent
import com.example.blueberry.ui.components.ValidateCard
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun ValidateScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onValidateSuccess: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    PaddedContent {
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
}

@PreviewScreenSizes
@Composable
fun ValidateScreenPreview() {
    ValidateScreen()
}

