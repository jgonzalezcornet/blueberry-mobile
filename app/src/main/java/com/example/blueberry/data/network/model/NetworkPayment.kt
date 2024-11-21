package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.Payment
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkPayment(
    var amount: Double,
    var description: String,
    var type: String,
    var cardId: Int?,
    var receiverEmail: String
) {
    fun asModel(): Payment {
        return Payment(
            amount = amount,
            description = description,
            type = type,
            cardId = cardId,
            receiverEmail = receiverEmail
        )
    }
}