package com.example.blueberry.data.network

import com.example.blueberry.data.network.api.WalletApiService
import com.example.blueberry.data.network.model.NetworkCard

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
}