package com.example.blueberry.data.network

import com.example.blueberry.data.network.api.UserApiService
import com.example.blueberry.data.network.model.NetworkCredentials
import com.example.blueberry.data.network.model.NetworkUser
import com.example.blueberry.SessionManager
import com.example.blueberry.data.network.model.NetworkCode
import com.example.blueberry.data.network.model.NetworkRegisterUser

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun register(firstName: String, lastName: String, birthDate: String, email: String, password: String): NetworkUser {
        return handleApiResponse { userApiService.register(NetworkRegisterUser(firstName, lastName, birthDate, email, password)) }
    }

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

    suspend fun verify(code: String): NetworkUser {
        return handleApiResponse { userApiService.verify(NetworkCode(code)) }
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { userApiService.getCurrentUser() }
    }
}