package com.example.blueberry.data.network

import com.example.blueberry.data.network.api.UserApiService
import com.example.blueberry.data.network.model.NetworkCredentials
import com.example.blueberry.data.network.model.NetworkUser
import com.example.blueberry.SessionManager

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            userApiService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { userApiService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { userApiService.getCurrentUser() }
    }
}