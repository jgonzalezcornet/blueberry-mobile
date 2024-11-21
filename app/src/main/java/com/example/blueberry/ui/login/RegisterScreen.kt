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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.blueberry.ui.components.PaddedContent
import com.example.blueberry.ui.components.RegisterCard
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
    PaddedContent {
        var showErrorDialog by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }
        val context = LocalContext.current

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = { showErrorDialog = false },
                title = {
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Red
                    )
                },
                text = {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showErrorDialog = false }) {
                        Text("Aceptar")
                    }
                },
                containerColor = Color.White
            )
        }

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterCard(
                onRegister = { name, lastName, email, birthDate, password, confirmPassword ->
                    when {
                        !checkPassword(password) -> {
                            errorMessage = context.getString(R.string.password_invalid_message)
                            showErrorDialog = true
                        }

                        !checkPasswordMismatch(password, confirmPassword) -> {
                            errorMessage = context.getString(R.string.password_mismatch_error)
                            showErrorDialog = true
                        }

                        else -> {
                            viewModel.register(name, lastName, birthDate, email, password)
                            onRegisterSuccess()
                        }
                    }
                }
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
}

@PreviewScreenSizes
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}

