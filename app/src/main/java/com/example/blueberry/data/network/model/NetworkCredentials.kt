package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NetworkCredentials(
    var email: String,
    var password: String
)
