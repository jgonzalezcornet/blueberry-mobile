package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkBalance

class Balance(
    var balance: Double
) {
    fun asNetworkModel(): NetworkBalance {
        return NetworkBalance(
            balance = balance
        )
    }
}