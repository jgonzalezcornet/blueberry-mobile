package com.example.blueberry.ui.home

import com.example.blueberry.data.model.Activity
import com.example.blueberry.data.model.Card
import com.example.blueberry.data.model.Error
import com.example.blueberry.data.model.User
import com.example.blueberry.data.model.WalletDetails

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val cards: List<Card>? = null,
    val currentCard: Card? = null,
    val error: Error? = null,
    val details: WalletDetails? = null,
    val activities: List<Activity>? = null,
    val form: Map<String, String>? = null
)