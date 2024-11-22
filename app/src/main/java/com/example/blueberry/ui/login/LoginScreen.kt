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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.data.model.Error
import com.example.blueberry.ui.components.LoginCard
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.home.HomeViewModel
import androidx.compose.ui.res.stringResource
import com.example.blueberry.R



@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {},
    onNavigateToSecurityInfo: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginCard(
                onForgotPassword = onForgotPassword,
                onLogin = { email, password ->
                    try {
                        viewModel.login(email, password, onLoginSuccess)
                    } catch(e: Exception) {
                        viewModel.setError(Error(400, e.message.toString()))
                    }
                }
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = getPadding()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.no_account_text),
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.register_link),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onNavigateToRegister() }
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
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onNavigateToTerms() }
                )
                Text(
                    text = stringResource(R.string.security_info),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onNavigateToSecurityInfo() }
                )
            }
        }

}

@PreviewScreenSizes
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

