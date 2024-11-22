package com.example.blueberry.data.network

import com.example.blueberry.data.network.api.PaymentApiService
import com.example.blueberry.data.network.model.NetworkActivityWrapper
import com.example.blueberry.data.network.model.NetworkBalance
import com.example.blueberry.data.network.model.NetworkPayment

class PaymentRemoteDataSource(
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {

    suspend fun makePayment(payment: NetworkPayment): NetworkBalance {
        return handleApiResponse {
            paymentApiService.makePayment(payment)
        }
    }

    suspend fun getPayments(): NetworkActivityWrapper {
        return handleApiResponse {
            paymentApiService.getPayments()
        }
    }

}