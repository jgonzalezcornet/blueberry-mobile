package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.Balance
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkBalance(
    var balance: Double
) {
    fun asModel(): Balance {
        return Balance(
            balance = balance
        )
    }
}