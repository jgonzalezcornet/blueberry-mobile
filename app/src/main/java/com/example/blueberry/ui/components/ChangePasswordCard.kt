package com.example.blueberry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme

@Composable
fun ChangePasswordCard(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showMismatchError by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = { onClose() }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.change_password_title),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_button),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClose() },
                        tint = Color.Gray
                    )
                }

                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    placeholder = { Text(stringResource(R.string.current_password_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = {
                        newPassword = it
                        showMismatchError = false // Reset error on input change
                    },
                    placeholder = { Text(stringResource(R.string.new_password_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = showMismatchError // Highlight the field in red if there's an error
                )

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        showMismatchError = false // Reset error on input change
                    },
                    placeholder = { Text(stringResource(R.string.confirm_new_password_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = showMismatchError // Highlight the field in red if there's an error
                )

                if (showMismatchError) {
                    Text(
                        text = stringResource(R.string.password_mismatch_error),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }

                Button(
                    onClick = {
                        if (newPassword == confirmPassword) {
                            // llamar a la API
                        } else {
                            showMismatchError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = stringResource(R.string.confirm_button),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun ChangePasswordCardPreview() {
    ChangePasswordCard()
}
