package com.example.blueberry.data.network.api

import com.example.blueberry.data.network.model.NetworkCredentials
import com.example.blueberry.data.network.model.NetworkToken
import com.example.blueberry.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {
    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<NetworkUser>
}