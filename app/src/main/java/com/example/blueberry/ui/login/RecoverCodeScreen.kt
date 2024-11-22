package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.blueberry.R
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.components.home.RecoverPasswordCodeCard
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.data.model.Error
import com.example.blueberry.utils.checkPassword
import com.example.blueberry.utils.checkPasswordMismatch

@Composable
fun RecoverCodeScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onRecoverCodeSuccess: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState

    var initialized by rememberSaveable(key = "initialized_key_code_screen") { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if(!initialized){
            initialized = true
            viewModel.initializeForm()
            viewModel.setFormValue("newPasswordVisible", "false")
            viewModel.setFormValue("confirmNewPasswordVisible", "false")
        }
    }

    var context = LocalContext.current

    Column(
        modifier = modifier
            .verticalScroll(
                enabled = true,
                state = rememberScrollState()
            )
            .padding(horizontal = getPadding())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecoverPasswordCodeCard(
            onCancel = onCancel,
            onRecoverCode = { code, password, confirmPassword ->
                when {
                    !checkPassword(password) -> {
                        viewModel.setError(Error(400, context.getString(R.string.password_invalid_message)))
                    }

                    !checkPasswordMismatch(password, confirmPassword) -> {
                        viewModel.setError(Error(400, context.getString(R.string.password_mismatch_error)))
                    }
                    else -> {
                        try {
                            viewModel.resetPassword(code, password, onRecoverCodeSuccess)
                        } catch(e: Exception) {
                            viewModel.setError(Error(400, e.message.toString()))
                        }
                    }
                }
            },
            onValueChange = { key, value -> viewModel.setFormValue(key, value) },
            code = uiState.form?.get("code") ?: "",
            newPassword = uiState.form?.get("newPassword") ?: "",
            confirmNewPassword = uiState.form?.get("confirmNewPassword") ?: "",
            newPasswordVisible = uiState.form?.get("newPasswordVisible") ?: "false",
            confirmNewPasswordVisible = uiState.form?.get("confirmNewPasswordVisible") ?: "false",
            onChangeNewPasswordVisibility = { value -> viewModel.setFormValue("newPasswordVisible", value) },
            onChangeConfirmNewPasswordVisibility = { value -> viewModel.setFormValue("confirmNewPasswordVisible", value) }
        )
    }

}