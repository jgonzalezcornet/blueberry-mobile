package com.example.blueberry.data.network

import com.example.blueberry.data.network.api.PaymentApiService
import com.example.blueberry.data.network.model.NetworkActivityWrapper
import com.example.blueberry.data.network.model.NetworkNewBalance
import com.example.blueberry.data.network.model.NetworkPayment

class PaymentRemoteDataSource(
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {

    suspend fun makePayment(payment: NetworkPayment): NetworkNewBalance {
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