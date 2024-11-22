package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkActivity
import com.example.blueberry.data.network.model.NetworkActivityWrapper

class ActivityWrapper(
    var payments: List<Activity>
){
    fun asNetworkModel(): NetworkActivityWrapper {
        return NetworkActivityWrapper(
            payments = payments.map { p -> p.onNetworkModel() }
        )
    }
}

class Activity(
    val id: Int?,
    val type: String,
    val amount: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val receiverBalanceBefore: Double,
    val receiverBalanceAfter: Double,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String,
    val card: Card?,
    val payer: User,
    val receiver: User
) {

    fun onNetworkModel(): NetworkActivity {
        return NetworkActivity(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            receiverBalanceBefore = receiverBalanceBefore,
            receiverBalanceAfter = receiverBalanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt,
            updatedAt = updatedAt,
            card = card?.asNetworkModel(),
            payer = payer.asNetworkModel(),
            receiver = receiver.asNetworkModel()
        )
    }

}