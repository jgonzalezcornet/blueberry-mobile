package com.example.blueberry.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.compose.ui.res.stringResource
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme

@Composable
fun AliasCard(
    modifier: Modifier = Modifier,
    balance: String,
    cbu: String,
    alias: String
) {
    var isBalanceVisible by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.balance_title),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isBalanceVisible) "\$${balance}" else "*****",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        imageVector = if (isBalanceVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(R.string.toggle_balance_visibility),
                        tint = Color.Gray,
                        modifier = Modifier
                            .clickable { isBalanceVisible = !isBalanceVisible }
                            .size(24.dp)
                    )
                }

                Text(
                    text = stringResource(R.string.balance_description),
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "CVU",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = stringResource(R.string.copy_cbu),
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                        .clickable { copyToClipboard(context, cbu, "CVU copiado")}
                        .size(16.dp)
                        )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = cbu,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Alias",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = stringResource(R.string.copy_alias),
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                        .clickable { copyToClipboard(context, alias, "Alias copiado") }
                        .size(16.dp)
                        )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = alias,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                }
            }
        }
    }
}

fun copyToClipboard(context: Context, text: String, message: String) {
    val clipboard = ContextCompat.getSystemService(context, android.content.ClipboardManager::class.java)
    val clip = android.content.ClipData.newPlainText("Copied Text", text)
    clipboard?.setPrimaryClip(clip)
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}