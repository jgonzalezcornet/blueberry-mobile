package com.example.blueberry.data.model

import com.example.blueberry.data.network.model.NetworkUser
import java.util.Date

data class User(
    var id: Int?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var birthDate: Date
){
    fun asNetworkModel(): NetworkUser {
        return NetworkUser(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            birthDate = birthDate.toString()
        )
    }

}

