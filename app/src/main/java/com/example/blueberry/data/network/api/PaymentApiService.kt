package com.example.blueberry.data.network.api

import com.example.blueberry.data.network.model.NetworkAlias
import com.example.blueberry.data.network.model.NetworkBalance
import com.example.blueberry.data.network.model.NetworkCard
import com.example.blueberry.data.network.model.NetworkPayment
import com.example.blueberry.data.network.model.NetworkWalletDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PaymentApiService {
    @POST("/api/payment")
    suspend fun makePayment(@Body payment: NetworkPayment): Response<NetworkBalance>


}