package com.example.blueberry.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.data.model.Error
import com.example.blueberry.ui.components.RegisterCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.utils.checkPassword
import com.example.blueberry.utils.checkPasswordMismatch


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {},
    onNavigateToSecurityInfo: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
        val context = LocalContext.current

        val uiState = viewModel.uiState

        var initialized by rememberSaveable(key = "initialized_key_register") { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            if(!initialized){
                initialized = true
                viewModel.initializeForm()
                viewModel.setFormValue("passwordVisible", "false")
                viewModel.setFormValue("confirmPasswordVisible", "false")
            }
        }

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
            RegisterCard(
                onRegister = { name, lastName, email, birthDate, password, confirmPassword ->
                    when {
                        !checkPassword(password) -> {
                            viewModel.setError(Error(400, context.getString(R.string.password_invalid_message)))
                        }

                        !checkPasswordMismatch(password, confirmPassword) -> {
                            viewModel.setError(Error(400, context.getString(R.string.password_mismatch_error)))
                        }

                        else -> {
                            try {
                                viewModel.register(name, lastName, birthDate, email, password, onRegisterSuccess)
                            } catch(e: Exception) {
                                viewModel.setError(Error(400, e.message.toString()))
                            }
                        }
                    }
                },
                name = uiState.form?.get("name") ?: "",
                lastName = uiState.form?.get("lastName") ?: "",
                email = uiState.form?.get("email") ?: "",
                birthDate = uiState.form?.get("birthDate") ?: "",
                birthMonth = uiState.form?.get("birthMonth") ?: "",
                birthYear = uiState.form?.get("birthYear") ?: "",
                password = uiState.form?.get("password") ?: "",
                confirmPassword = uiState.form?.get("confirmPassword") ?: "",
                onValueChange = { key, value -> viewModel.setFormValue(key, value) },
                passwordVisible = uiState.form?.get("passwordVisible") ?: "false",
                confirmPasswordVisible = uiState.form?.get("confirmPasswordVisible") ?: "false",
                onChangePasswordVisibility = { value -> viewModel.setFormValue("passwordVisible", value) },
                onChangeConfirmPasswordVisibility = { value -> viewModel.setFormValue("confirmPasswordVisible", value) },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.have_account),
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.login_title),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onNavigateBack() }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.terms_and_conditions),
                    color = Color.Gray.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onNavigateToTerms() }
                )
                Text(
                    text = stringResource(R.string.security_info),
                    color = Color.Gray.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onNavigateToSecurityInfo() }
                )
            }
        }

}