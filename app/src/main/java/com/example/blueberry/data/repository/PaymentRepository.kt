package com.example.blueberry.data.repository

import com.example.blueberry.data.model.ActivityWrapper
import com.example.blueberry.data.model.NewBalance
import com.example.blueberry.data.model.Payment
import com.example.blueberry.data.network.PaymentRemoteDataSource

class PaymentRepository(
    private val remoteDataSource: PaymentRemoteDataSource
) {
    suspend fun makePayment(payment: Payment): NewBalance {
        return remoteDataSource.makePayment(payment.asNetworkModel()).asModel()
    }

    suspend fun getPayments(): ActivityWrapper {
        return remoteDataSource.getPayments().asModel()
    }
}