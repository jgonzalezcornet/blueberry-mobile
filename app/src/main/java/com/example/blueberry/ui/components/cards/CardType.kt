package com.example.blueberry.ui.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.blueberry.R

enum class CardType(
    val backgroundColor: Color,
    @DrawableRes val logo: Int
) {
    VISA(Color(0xFF1A1F71), R.drawable.visa),
    MASTERCARD(Color(0xFFEB001B), R.drawable.mastercard),
    AMEX(Color(0xFF2E77BC), R.drawable.amex),
    OTHER(Color.Gray, R.drawable.unknown);

    companion object {
        fun fromCardNumber(cardNumber: String): CardType {
            return when {
                cardNumber.startsWith("4") -> VISA
                cardNumber.startsWith("51") || cardNumber.startsWith("55") -> MASTERCARD
                cardNumber.startsWith("34") || cardNumber.startsWith("37") -> AMEX
                else -> OTHER
            }
        }
    }
} 