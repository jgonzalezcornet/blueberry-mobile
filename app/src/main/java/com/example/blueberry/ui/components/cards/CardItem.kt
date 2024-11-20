package com.example.blueberry.ui.components.cards

data class CardItem(
    val cardNumber: String,
    val cardHolderName: String,
    val expiryMonth: String,
    val expiryYear: String,
    val cvv: String
) {
    val cardType: CardType = CardType.fromCardNumber(cardNumber)
    val lastFourDigits: String = cardNumber.takeLast(4)
} 