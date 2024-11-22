package com.example.blueberry.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R


@Composable
fun RecoverPasswordCodeCard(
    onCancel: () -> Unit = {},
    onRecoverCode: (String, String, String) -> Unit,
    code: String,
    newPassword: String,
    confirmNewPassword: String,
    onValueChange: (String, String) -> Unit,
    newPasswordVisible: String,
    confirmNewPasswordVisible: String,
    onChangeNewPasswordVisibility: (String) -> Unit,
    onChangeConfirmNewPasswordVisibility: (String) -> Unit,
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.recover_password_code_title),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.recover_password_code_message),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start)
            )
            OutlinedTextField(
                value = code,
                onValueChange = { onValueChange("code", it) },
                label = {
                    Text(
                        stringResource(R.string.code_label),
                        color = Color.Gray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.new_password_code_message),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start)
            )
            OutlinedTextField(
                value = newPassword,
                onValueChange = { onValueChange("newPassword", it) },
                label = {
                    Text(
                        stringResource(R.string.register_new_password_label),
                        color = Color.Gray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (newPasswordVisible == "true") VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { onChangeNewPasswordVisibility(if (newPasswordVisible == "true") "false" else "true") }) {
                            Icon(
                                imageVector = if (newPasswordVisible == "true") Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (newPasswordVisible == "true") "Ocultar contraseña" else "Mostrar contraseña",
                                tint = Color.Gray
                            )
                        }
                    }
            )
            OutlinedTextField(
                value = confirmNewPassword,
                onValueChange = { onValueChange("confirmNewPassword", it) },
                label = {
                    Text(
                        stringResource(R.string.register_new_password_confirm_label),
                        color = Color.Gray
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (confirmNewPasswordVisible == "true") VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { onChangeConfirmNewPasswordVisibility( if(confirmNewPasswordVisible == "true") "false" else "true") }) {
                        Icon(
                            imageVector = if (confirmNewPasswordVisible == "true") Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (confirmNewPasswordVisible == "true") stringResource(R.string.hide_password) else stringResource(R.string.show_password),
                            tint = Color.Gray
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button (
        onClick = { onRecoverCode(code, newPassword, confirmNewPassword) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.recover_button),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = { onCancel() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                )
            ) {
                Text(
                    text = stringResource(R.string.recover_cancel_button),
                    color = Color.White
                )
            }
        }
    }
}
