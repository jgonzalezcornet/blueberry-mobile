package com.example.blueberry.ui.components.activity

import java.sql.Date

data class ActivityItem(
    val date: Date,
    val activityType: ActivityType,
    val user: String,
    val amount: Double
)