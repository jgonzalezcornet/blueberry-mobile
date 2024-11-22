package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkNewBalance

class NewBalance(
    var newBalance: Double
) {
    fun asNetworkModel(): NetworkNewBalance {
        return NetworkNewBalance(
            newBalance = newBalance
        )
    }
}