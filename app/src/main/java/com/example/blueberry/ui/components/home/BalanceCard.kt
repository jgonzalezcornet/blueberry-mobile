package com.example.blueberry.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun BalanceCard(
    modifier: Modifier = Modifier,
    balance: String
) {
    var isBalanceVisible by remember { mutableStateOf(true) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isBalanceVisible) "\$${balance}" else "*****",
                        style = TextStyle(
                            fontSize = 28.sp,
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
            }
        }
    }
}

