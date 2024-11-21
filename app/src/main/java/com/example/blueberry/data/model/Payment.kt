package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkPayment

data class Payment (
    var amount: Double,
    var description: String,
    var type: String,
    var cardId: Int?,
    var receiverEmail: String
) {
    fun asNetworkModel(): NetworkPayment {
        return NetworkPayment(
            amount = amount,
            description = description,
            type = type,
            cardId = cardId,
            receiverEmail = receiverEmail
        )
    }
}