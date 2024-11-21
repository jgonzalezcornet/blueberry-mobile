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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.RegisterCard
import androidx.compose.material3.MaterialTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {},
    onNavigateToSecurityInfo: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterCard()
        
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

@PreviewScreenSizes
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}

