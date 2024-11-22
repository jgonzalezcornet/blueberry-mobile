package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.NewBalance
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkNewBalance(
    var newBalance: Double
) {
    fun asModel(): NewBalance {
        return NewBalance(
            newBalance = newBalance
        )
    }
}