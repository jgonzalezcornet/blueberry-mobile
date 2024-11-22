package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.data.model.Error
import com.example.blueberry.ui.components.ValidateCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel

@Composable
fun ValidateScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onValidateSuccess: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_validate") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            initialized = true
            viewModel.initializeForm()
        }
    }

        Column(
            modifier = modifier
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ValidateCard(
                onCancel = onCancel,
                onValidate = {
                    try {
                        viewModel.verify(uiState.form?.get("code") ?: "", onValidateSuccess)
                    } catch(e: Exception) {
                        viewModel.setError(Error(400, e.message.toString()))
                    }
                },
                code = uiState.form?.get("code") ?: "",
                onValueChange = { key, value -> viewModel.setFormValue(key, value) }
            )
        }

}