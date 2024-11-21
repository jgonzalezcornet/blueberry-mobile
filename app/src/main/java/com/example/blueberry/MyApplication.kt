package com.example.blueberry

import android.app.Application
import com.example.blueberry.data.network.PaymentRemoteDataSource
import com.example.blueberry.data.network.UserRemoteDataSource
import com.example.blueberry.data.network.WalletRemoteDataSource
import com.example.blueberry.data.network.api.RetrofitClient
import com.example.blueberry.data.repository.PaymentRepository
import com.example.blueberry.data.repository.UserRepository
import com.example.blueberry.data.repository.WalletRepository

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val walletRemoteDataSource: WalletRemoteDataSource
        get() = WalletRemoteDataSource(RetrofitClient.getWalletApiService(this))

    private val paymentRemoteDataSource: PaymentRemoteDataSource
        get() = PaymentRemoteDataSource(RetrofitClient.getPaymentApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val walletRepository: WalletRepository
        get() = WalletRepository(walletRemoteDataSource)

    val paymentRepository: PaymentRepository
        get() = PaymentRepository(paymentRemoteDataSource)

}