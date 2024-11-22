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
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme


@Composable
fun ChangeAliasCard(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    onConfirm: (String) -> Unit = {},
    newAlias: String,
    onValueChange: (String, String) -> Unit
) {

    Dialog(onDismissRequest = { onClose() }) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.change_alias_title),
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

                Text(
                    text = stringResource(R.string.change_alias_description),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = newAlias,
                    onValueChange = { it -> if (it.all { char -> char.isLetterOrDigit() || char == '.' }) {
                        onValueChange("newAlias", it)
                    }
                    },
                    placeholder = {
                        Text(
                            stringResource(R.string.change_alias_placeholder),
                            color = Color.Gray
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Button(
                    onClick = { if (newAlias.isNotEmpty()) onConfirm(newAlias) },
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
