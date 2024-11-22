package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.Email
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkEmail (
    var email: String
) {
    fun asNetworkModel(): Email {
        return Email(
            email = email
        )
    }
}