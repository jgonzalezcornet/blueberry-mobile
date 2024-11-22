package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkEmail

class Email (
    var email: String
) {
    fun asNetworkModel(): NetworkEmail {
        return NetworkEmail(
            email = email
        )
    }
}