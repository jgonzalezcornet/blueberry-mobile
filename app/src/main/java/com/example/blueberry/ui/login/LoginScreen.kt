package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.ui.components.LoginCard
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {},
    onNavigateToSecurityInfo: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginCard(
            onLoginSuccess = onLoginSuccess,
            onForgotPassword = onForgotPassword
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿No tienes una cuenta?",
                color = Color.Black,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Registrarse",
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
                text = "Términos y Condiciones",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onNavigateToTerms() }
            )
            Text(
                text = "Información de Seguridad",
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

