package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkBalance
import com.example.blueberry.data.network.model.NetworkRecharge
import java.time.temporal.TemporalAmount

class Recharge(
    var amount: Double
) {
    fun asNetworkModel(): NetworkRecharge {
        return NetworkRecharge(
            amount = amount
        )
    }
}