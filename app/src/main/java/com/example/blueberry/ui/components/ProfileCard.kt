package com.example.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import androidx.compose.ui.res.stringResource

@Composable
fun ProfileCard(
    firstName: String,
    lastName: String,
    email: String,
    alias: String,
    cbu: String,
    onChangeAliasClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onLogout: () -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
        )
        {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp), 
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header with the title
            Text(
                text = stringResource(R.string.profile_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Information display
            ProfileInfoRow(label = stringResource(R.string.profile_name), value = firstName)
            ProfileInfoRow(label = stringResource(R.string.profile_surname), value = lastName)
            ProfileInfoRow(label = stringResource(R.string.profile_email), value = obfuscateEmail(email))
            ProfileInfoRow(label = stringResource(R.string.profile_alias), value = alias)
            ProfileInfoRow(label = stringResource(R.string.profile_cvu), value = cbu)

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            CustomButton(
                text = stringResource(R.string.change_alias),
                onClick = onChangeAliasClick
            )

            CustomButton(
                text = stringResource(R.string.change_password),
                onClick = onChangePasswordClick
            )

            CustomButton(
                text = stringResource(R.string.logout),
                onClick = { onLogout() }
            )
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

fun obfuscateEmail(email: String): String {
    val username = email.substringBefore("@")
    val domain = email.substringAfter("@")
    return "${username.take(4)}****@$domain"
}

@PreviewScreenSizes
@Composable
fun ProfileCardPreview() {
    ProfileCard("Juan Ignacio Lategana", "45748655", "j@lategana.com", "jua.blueberry", "313123213123", {}, {}, {})
}
