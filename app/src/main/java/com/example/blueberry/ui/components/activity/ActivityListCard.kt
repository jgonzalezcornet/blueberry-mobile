package com.example.blueberry.ui.components.activity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blueberry.R
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ActivityListCard(activities: List<ActivityItem>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright)
    ) {
        val formattedActivities = formatActivities(activities)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            formattedActivities.forEach { (dateLabel, items) ->
                // Header for each date group
                item {
                    Text(
                        text = dateLabel,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )
                }

                // List of activities for the date
                items(items) { activity ->
                    ActivityCard(
                        activityItem = activity,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    )
                }

                // Divider between date groups
                item {
                    Divider(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                }
            }
        }
    }
}

// Helper function to format the dates
@Composable
fun formatActivities(activities: List<ActivityItem>): List<Pair<String, List<ActivityItem>>> {
    val currentCalendar = Calendar.getInstance()
    val grouped = activities.groupBy { activity ->
        val activityCalendar = Calendar.getInstance().apply { time = activity.date }

        when {
            currentCalendar.get(Calendar.YEAR) == activityCalendar.get(Calendar.YEAR) &&
                    currentCalendar.get(Calendar.DAY_OF_YEAR) == activityCalendar.get(Calendar.DAY_OF_YEAR) -> {
                stringResource(R.string.today_string)
            }
            currentCalendar.get(Calendar.YEAR) == activityCalendar.get(Calendar.YEAR) -> {
                SimpleDateFormat(stringResource(R.string.date_format), Locale.getDefault()).format(activity.date)
            }
            else -> {
                SimpleDateFormat(stringResource(R.string.date_format_with_year), Locale.getDefault()).format(activity.date)            }
        }
    }

    return grouped.entries
        .sortedByDescending { it.value.first().date } // Sort by the actual date
        .map { (dateLabel, activities) -> dateLabel to activities }
}




@Preview(showBackground = true)
@Composable
fun ActivityListCardPreview() {
    val activities = listOf(
        ActivityItem(Date(2024 - 1900, 10, 19), ActivityType.SENT, "Nicolás Priotto", 3000.0),
        ActivityItem(Date(2024 - 1900, 10, 19), ActivityType.SENT, "Nicolás Priotto", 3000.0),
        ActivityItem(Date(2024 - 1900, 10, 19), ActivityType.RECEIVED, "Pedro Alvarado", 5000.0),
        ActivityItem(Date(2024 - 1900, 6, 7), ActivityType.SENT, "Josefina Gonzalez", 3000.0),
        ActivityItem(Date(2024 - 1900, 6, 5), ActivityType.RECEIVED, "Manuel Ahumada", 25000.0),
        ActivityItem(Date(2023 - 1900, 5, 30), ActivityType.PAYMENT, "Atuel", 25000.0)
    )

    ActivityListCard(activities = activities)
}