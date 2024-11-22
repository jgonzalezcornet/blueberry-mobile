package com.example.blueberry.data.network

import com.example.blueberry.data.network.api.WalletApiService
import com.example.blueberry.data.network.model.NetworkAlias
import com.example.blueberry.data.network.model.NetworkBalance
import com.example.blueberry.data.network.model.NetworkCard
import com.example.blueberry.data.network.model.NetworkNewBalance
import com.example.blueberry.data.network.model.NetworkRecharge
import com.example.blueberry.data.network.model.NetworkWalletDetails

class WalletRemoteDataSource(
    private val walletApiService: WalletApiService
) : RemoteDataSource() {

    suspend fun getCards(): List<NetworkCard> {
        return handleApiResponse {
            walletApiService.getCards()
        }
    }

    suspend fun addCard(card: NetworkCard): NetworkCard {
        return handleApiResponse {
            walletApiService.addCard(card)
        }
    }

    suspend fun deleteCard(cardId: Int) {
        handleApiResponse {
            walletApiService.deleteCard(cardId)
        }

    }

    suspend fun getWalletDetails(): NetworkWalletDetails {
        return handleApiResponse {
            walletApiService.getWalletDetails()
        }
    }

    suspend fun updateAlias(alias: NetworkAlias) {
        handleApiResponse {
            walletApiService.updateAlias(alias)
        }
    }

    suspend fun recharge(recharge: NetworkRecharge): NetworkNewBalance {
        return handleApiResponse {
            walletApiService.recharge(recharge)
        }
    }

}