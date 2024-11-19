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
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueberry.R
import com.example.blueberry.ui.theme.SecondaryPink

@Composable
fun AliasCard(
    modifier: Modifier = Modifier,
    balance: String = "$ 14.017,57",
    cbu: String = "2000000000500001234",
    alias: String = "jlategana.blueberry"
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
                // Título
                Text(
                    text = stringResource(R.string.balance_title),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                // Balance y botón para ocultar/mostrar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isBalanceVisible) balance else "*****",
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

                // Descripción
                Text(
                    text = stringResource(R.string.balance_description),
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // CVU
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "CVU",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = stringResource(R.string.copy_cbu),
                        tint = SecondaryPink,
                        modifier = Modifier
                        .clickable { copyToClipboard(context, cbu, "CVU copiado")}
                        .size(16.dp)
                        )
                    Spacer(Modifier.weight(1f))
                    Text(text = cbu)
                }

                // Alias
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Alias",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                    )
                    Spacer(Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = stringResource(R.string.copy_alias),
                        tint = SecondaryPink,
                        modifier = Modifier
                        .clickable { copyToClipboard(context, alias, "Alias copiado") }
                        .size(16.dp)
                        )
                    Spacer(Modifier.weight(1f))
                    Text(text = alias)
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

@Preview(showBackground = true)
@Composable
fun AliasCardPreview() {
    AliasCard()
}