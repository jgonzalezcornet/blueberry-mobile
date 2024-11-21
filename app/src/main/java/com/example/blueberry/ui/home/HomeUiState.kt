package com.example.blueberry.ui.home

import com.example.blueberry.data.model.Card
import com.example.blueberry.data.model.Error
import com.example.blueberry.data.model.User

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val cards: List<Card>? = null,
    val currentCard: Card? = null,
    val error: Error? = null
)

val HomeUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val HomeUiState.canGetAllCards: Boolean get() = isAuthenticated
val HomeUiState.canAddCard: Boolean get() = isAuthenticated
val HomeUiState.canDeleteCard: Boolean get() = isAuthenticated && currentCard != null
