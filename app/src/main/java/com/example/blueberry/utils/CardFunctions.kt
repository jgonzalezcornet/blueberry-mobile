package com.example.blueberry.utils

fun formatDate(month: String, year: String): String {
    return if (month.isNotEmpty() || year.isNotEmpty()) {
        "${month.take(2)}/${year.take(2)}"
    } else ""
}