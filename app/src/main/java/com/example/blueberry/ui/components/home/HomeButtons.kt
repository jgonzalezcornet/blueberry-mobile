package com.example.blueberry.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.R
import com.example.blueberry.ui.theme.PrimaryBlue

@Composable
fun HomeButtons(
    onInsertMoneyClick: () -> Unit = {},
    onTransferMoneyClick: () -> Unit = {},
    onChargeMoneyClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ActionButton(
            icon = painterResource(R.drawable.insertmoney),
            text = stringResource(R.string.insert_money),
            onClick = onInsertMoneyClick // Call onInsertMoneyClick when clicked
        )
        ActionButton(
            icon = painterResource(R.drawable.link),
            text = stringResource(R.string.charge_money),
            onClick = onChargeMoneyClick // Call onTransferMoneyClick when clicked
        )
        ActionButton(
            icon = painterResource(R.drawable.transfer),
            text = stringResource(R.string.transfer_money),
            onClick = onTransferMoneyClick // Call onChargeMoneyClick when clicked
        )
    }
}

@Composable
fun ActionButton(
    icon: Painter,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable {onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(color = PrimaryBlue, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = MaterialTheme.colorScheme.surfaceBright,
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActionButtonsPreview() {
    HomeButtons()
}

