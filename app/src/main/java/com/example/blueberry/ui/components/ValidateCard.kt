package com.example.blueberry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R

@Composable
fun ValidateCard(
    onCancel: () -> Unit = {},
    onValidate: (String) -> Unit,
    code: String,
    onValueChange: (String, String) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.validate_email_title),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.validate_email_message),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray,
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
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
            Button(
                onClick = { onValidate(code) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.validate_register),
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
                    text = stringResource(R.string.cancel_button),
                    color = Color.White
                )
            }
        }
    }
}

/*
@PreviewScreenSizes
@Composable
fun ValidateCardPreview() {
    ValidateCard()
}
*/
