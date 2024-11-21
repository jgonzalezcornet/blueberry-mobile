package com.example.blueberry.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import com.example.blueberry.ui.components.activity.ActivityCard
import com.example.blueberry.ui.components.activity.ActivityItem
import com.example.blueberry.ui.components.activity.ActivityType

@Composable
fun LastActivityCard(
    activity: ActivityItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    ) {
        Column{
            Text(
                text = stringResource(R.string.last_activity_string),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 1.dp)
            )
            ActivityCard(activity)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LastActivityCardPreview() {
    val activity = ActivityItem(java.sql.Date(2024 - 1900, 10, 19), ActivityType.SENT, "Nicolás Priotto", 3000.0)
    LastActivityCard(activity = activity)

}