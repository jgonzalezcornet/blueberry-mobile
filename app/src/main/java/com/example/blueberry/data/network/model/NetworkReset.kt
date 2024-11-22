package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.Reset
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkReset (
    var code: String,
    var password: String
) {
    fun asNetworkModel(): Reset {
        return Reset(
            code = code,
            password = password
        )
    }
}