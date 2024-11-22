package com.example.blueberry.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.blueberry.MyApplication
import com.example.blueberry.data.DataSourceException
import com.example.blueberry.data.model.Card
import com.example.blueberry.data.model.Error
import com.example.blueberry.data.repository.UserRepository
import com.example.blueberry.data.repository.WalletRepository
import com.example.blueberry.data.repository.PaymentRepository
import com.example.blueberry.data.network.model.NetworkAlias
import com.example.blueberry.SessionManager
import com.example.blueberry.data.model.Balance
import com.example.blueberry.data.model.Payment
import com.example.blueberry.data.model.Recharge
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun register(firstName: String, lastName: String, birthDate: String, email: String, password: String, callback: () -> Unit) = runOnViewModelScope(
        { userRepository.register(firstName, lastName, birthDate, email, password) },
        { state, _ -> state.copy() },
        callback
    )

    fun login(username: String, password: String, callback: () -> Unit) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) },
        callback
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                currentCard = null,
                cards = null,
                details = null
            )
        }
    )

    fun verify(code: String) = runOnViewModelScope(
        { userRepository.verify(code) },
        { state, _ -> state.copy() }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun recoverPassword(email: String, callback: () -> Unit) = runOnViewModelScope(
        { userRepository.recoverPassword(email) },
        { state, _ -> state.copy() },
        callback
    )

    fun resetPassword(code: String, password: String, callback: () -> Unit) = runOnViewModelScope(
        { userRepository.resetPassword(code, password) },
        { state, _ -> state.copy() },
        callback
    )

    fun getCards() = runOnViewModelScope(
        { walletRepository.getCards(true) },
        { state, response -> state.copy(cards = response) }
    )

    fun addCard(card: Card) = runOnViewModelScope(
        {
            walletRepository.addCard(card)
        },
        { state, response ->
            state.copy(
                currentCard = response,
                cards = null
            )
        }
    )

    fun deleteCard(cardId: Int) = runOnViewModelScope(
        { walletRepository.deleteCard(cardId) },
        { state, _ ->
            state.copy(
                currentCard = null,
                cards = null
            )
        }
    )

    fun getWalletDetails() = runOnViewModelScope(
        { walletRepository.getWalletDetails() },
        { state, response -> state.copy(details = response) }
    )

    fun updateAlias(alias: String, callback: () -> Unit) = runOnViewModelScope(
        { walletRepository.updateAlias(NetworkAlias(alias)) },
        { state, _ -> state.copy() },
        callback
    )

    fun recharge(amount: Recharge, callback: () -> Unit) = runOnViewModelScope(
        { walletRepository.recharge(amount) },
        { state, _ -> state.copy() },
        callback
    )

    fun makePayment(payment: Payment, callback: () -> Unit) = runOnViewModelScope(
        { paymentRepository.makePayment(payment) },
        { state, response -> state.copy() },
        callback
    )

    fun getPayments() = runOnViewModelScope(
        { paymentRepository.getPayments() },
        { state, response -> state.copy(activities = response.payments) }
    )

    fun clearError() = runOnViewModelScope(
        { },
        { state, _ -> state.copy(error = null) }
    )

    fun setError(error: Error) = runOnViewModelScope(
        { },
        { state, _ -> state.copy(error = error) }
    )

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (HomeUiState, R) -> HomeUiState,
        callback: () -> Unit = { }
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
            callback()
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "")
        } else {
            Error(null, e.message ?: "")
        }
    }

    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: MyApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    application.sessionManager,
                    application.userRepository,
                    application.walletRepository,
                    application.paymentRepository) as T
            }
        }
    }
}