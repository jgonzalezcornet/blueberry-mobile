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
import com.example.blueberry.ui.theme.PrimaryBlue

@Composable
fun ProfileCard(
    name: String,
    dni: String,
    email: String,
    alias: String,
    cbu: String,
    onChangeAliasClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp), // Inner padding for spacing
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Header with the title
        Text(
            text = "Perfil de Usuario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Information display
        ProfileInfoRow(label = "Nombre", value = name)
        ProfileInfoRow(label = "DNI", value = dni)
        ProfileInfoRow(label = "Email", value = obfuscateEmail(email))
        ProfileInfoRow(label = "Alias", value = alias)
        ProfileInfoRow(label = "CVU", value = cbu)

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        CustomButton(
            text = "Cambiar Alias",
            onClick = onChangeAliasClick
        )

        CustomButton(
            text = "Cambiar Contraseña",
            onClick = onChangePasswordClick
        )

        CustomButton(
            text = "Cerrar Sesión",
            onClick = onLogoutClick
        )
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
            color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
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
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
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
