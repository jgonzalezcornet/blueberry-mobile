package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkReset

class Reset (
    var code: String,
    var password: String
) {
    fun asNetworkModel(): NetworkReset {
        return NetworkReset(
            code = code,
            password = password
        )
    }
}