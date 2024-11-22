package com.example.blueberry.ui.components.activity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.blueberry.R

enum class ActivityType(
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    SENT(R.string.transfer_sent, R.drawable.transfericon),
    RECEIVED(R.string.transfer_received, R.drawable.transfericon);
}