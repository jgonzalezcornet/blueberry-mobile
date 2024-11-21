package com.example.blueberry.data.repository

import com.example.blueberry.data.model.Balance
import com.example.blueberry.data.model.Card
import com.example.blueberry.data.model.Recharge
import com.example.blueberry.data.model.WalletDetails
import com.example.blueberry.data.network.WalletRemoteDataSource
import com.example.blueberry.data.network.model.NetworkAlias
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class WalletRepository(
    private val remoteDataSource: WalletRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val cardsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var cards: List<Card> = emptyList()

    suspend fun getCards(refresh: Boolean = false): List<Card> {
        if (refresh || cards.isEmpty()) {
            val result = remoteDataSource.getCards()
            // Thread-safe write to sports
            cardsMutex.withLock {
                this.cards = result.map { it.asModel() }
            }
        }

        return cardsMutex.withLock { this.cards }
    }

    suspend fun addCard(card: Card): Card {
        val newCard = remoteDataSource.addCard(card.asNetworkModel()).asModel()
        cardsMutex.withLock {
            this.cards = emptyList()
        }
        return newCard
    }

    suspend fun deleteCard(cardId: Int) {
        remoteDataSource.deleteCard(cardId)
        cardsMutex.withLock {
            this.cards = emptyList()
        }

    }

    suspend fun getWalletDetails(): WalletDetails {
        return remoteDataSource.getWalletDetails().asModel()
    }

    suspend fun updateAlias(alias: NetworkAlias) {
        remoteDataSource.updateAlias(alias)
    }

    suspend fun recharge(recharge: Recharge): Balance {
        return remoteDataSource.recharge(recharge.asNetworkModel()).asModel()
    }

}