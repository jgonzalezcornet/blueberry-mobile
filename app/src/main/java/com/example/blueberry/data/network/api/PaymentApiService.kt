package com.example.blueberry.data.network.api

import com.example.blueberry.data.network.model.NetworkActivityWrapper
import com.example.blueberry.data.network.model.NetworkBalance
import com.example.blueberry.data.network.model.NetworkPayment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PaymentApiService {
    @POST("/api/payment")
    suspend fun makePayment(@Body payment: NetworkPayment): Response<NetworkBalance>

    @GET("/api/payment")
    suspend fun getPayments(): Response<NetworkActivityWrapper>

}