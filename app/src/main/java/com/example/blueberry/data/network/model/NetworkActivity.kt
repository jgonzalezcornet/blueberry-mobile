package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.Activity
import com.example.blueberry.data.model.ActivityWrapper
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkActivityWrapper(
    var payments: List<NetworkActivity>
) {
    fun asModel(): ActivityWrapper {
        return ActivityWrapper(
            payments = payments.map { p -> p.asModel() }
        )
    }
}

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkActivity(
    var id: Int?,
    var type: String,
    var amount: Double,
    var balanceBefore: Double,
    var balanceAfter: Double,
    var receiverBalanceBefore: Double,
    var receiverBalanceAfter: Double,
    var pending: Boolean,
    var linkUuid: String?,
    var createdAt: String,
    var updatedAt: String,
    var card: NetworkCard?,
    var payer: NetworkUser,
    var receiver: NetworkUser
) {
    fun asModel(): Activity{
        return Activity(
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
            card = card?.asModel(),
            payer = payer.asModel(),
            receiver = receiver.asModel()
        )

    }
}