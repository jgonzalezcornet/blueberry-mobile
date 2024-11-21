package com.example.blueberry.data.network.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class NetworkRegisterUser(
    var firstName: String,
    var lastName: String,
    var birthDate: String,
    var email: String,
    var password: String
) {}