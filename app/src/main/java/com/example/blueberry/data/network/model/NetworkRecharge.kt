package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.Recharge
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkRecharge(
    var amount: Double
) {
    fun asModel(): Recharge {
        return Recharge(
            amount = amount
        )
    }
}