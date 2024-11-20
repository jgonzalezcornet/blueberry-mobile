package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueberry.R
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.components.activity.ActivityItem
import com.example.blueberry.ui.components.activity.ActivityListCard
import com.example.blueberry.ui.components.activity.ActivityType
import java.sql.Date

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {}
) {
    val activities = listOf(
        ActivityItem(Date(2023 - 1900, 11, 15), ActivityType.SENT, "Carlos Mendoza", 4500.0),
        ActivityItem(Date(2024 - 1900, 3, 10), ActivityType.RECEIVED, "Ana Ramirez", 3200.0),
        ActivityItem(Date(2023 - 1900, 11, 15), ActivityType.SENT, "Carlos Mendoza", 4500.0),
        ActivityItem(Date(2023 - 1900, 7, 22), ActivityType.PAYMENT, "Verónica Torres", 18000.0),
        ActivityItem(Date(2024 - 1900, 3, 10), ActivityType.RECEIVED, "Ana Ramirez", 3200.0),
        ActivityItem(Date(2024 - 1900, 8, 5), ActivityType.SENT, "Eduardo Lopez", 7000.0),
        ActivityItem(Date(2023 - 1900, 9, 19), ActivityType.PAYMENT, "Diego Luna", 14000.0),
        ActivityItem(Date(2024 - 1900, 1, 12), ActivityType.RECEIVED, "Lucía Alvarez", 5000.0),
        ActivityItem(Date(2023 - 1900, 5, 30), ActivityType.SENT, "Gabriel Ortiz", 3000.0),
        ActivityItem(Date(2024 - 1900, 6, 7), ActivityType.PAYMENT, "Sandra Perez", 12000.0),
        ActivityItem(Date(2024 - 1900, 6, 7), ActivityType.SENT, "Eduardo Lopez", 7000.0),
        ActivityItem(Date(2023 - 1900, 9, 19), ActivityType.RECEIVED, "Diego Luna", 14000.0),
        ActivityItem(Date(2024 - 1900, 3, 10), ActivityType.SENT, "Lucía Alvarez", 5000.0),
        ActivityItem(Date(2023 - 1900, 11, 15), ActivityType.SENT, "Carlos Mendoza", 4500.0),
        ActivityItem(Date(2024 - 1900, 8, 5), ActivityType.PAYMENT, "Sandra Perez", 12000.0),
        ActivityItem(Date(2024 - 1900, 1, 12), ActivityType.SENT, "Gabriel Ortiz", 3000.0),
        ActivityItem(Date(2023 - 1900, 5, 30), ActivityType.PAYMENT, "Diego Luna", 14000.0),
        ActivityItem(Date(2023 - 1900, 7, 22), ActivityType.RECEIVED, "Verónica Torres", 18000.0),
        ActivityItem(Date(2024 - 1900, 8, 5), ActivityType.RECEIVED, "Eduardo Lopez", 7000.0),
        ActivityItem(Date(2024 - 1900, 6, 7), ActivityType.PAYMENT, "Sandra Perez", 12000.0),
        ActivityItem(Date(2024 - 1900, 10, 19), ActivityType.PAYMENT, "Sandra Perez", 123000.0)
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(R.string.activity_title),
            onBackNavigation = onBackNavigation
        )
        ActivityListCard(activities)
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityScreenPreview() {
    ActivityScreen()
}

