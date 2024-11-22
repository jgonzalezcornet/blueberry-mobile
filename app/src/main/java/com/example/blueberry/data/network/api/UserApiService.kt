package com.example.blueberry.data.network.api

import com.example.blueberry.data.network.model.NetworkCode
import com.example.blueberry.data.network.model.NetworkCredentials
import com.example.blueberry.data.network.model.NetworkEmail
import com.example.blueberry.data.network.model.NetworkReset
import com.example.blueberry.data.network.model.NetworkRegisterUser
import com.example.blueberry.data.network.model.NetworkToken
import com.example.blueberry.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("/api/user/verify")
    suspend fun verify(@Body credentials: NetworkCode): Response<NetworkUser>
    
    @POST("/api/user")
    suspend fun register(@Body credentials: NetworkRegisterUser): Response<NetworkUser>

    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @POST("user/recover-password")
    suspend fun recoverPassword(@Body email: NetworkEmail): Response<Unit>

    @POST("user/reset-password")
    suspend fun resetPassword(@Body reset: NetworkReset): Response<Unit>
}