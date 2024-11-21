package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import com.example.blueberry.data.model.WalletDetails
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkWalletDetails(
    var id: Int?,
    var balance: Double,
    var invested: Double,
    var cbu: String,
    var alias: String,
    var createdAt: String?,
    var updatedAt: String?
) {
    fun asModel(): WalletDetails {
        return WalletDetails(
            id = id,
            balance = balance,
            invested = invested,
            cbu = cbu,
            alias = alias
        )
    }
}