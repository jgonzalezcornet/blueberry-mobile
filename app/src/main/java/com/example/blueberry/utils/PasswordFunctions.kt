package com.example.blueberry.utils

fun checkPassword(password: String): Boolean {
    val hasMinLength = password.length >= 8
    
    val hasLowerCase = password.any { it.isLowerCase() }
    
    val hasUpperCase = password.any { it.isUpperCase() }
    
    val hasNumber = password.any { it.isDigit() }
    
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    
    val hasNoÑ = !password.contains('ñ', ignoreCase = true)
    
    return hasMinLength && 
           hasLowerCase && 
           hasUpperCase && 
           hasNumber && 
           hasSpecialChar && 
           hasNoÑ
}

fun checkPasswordMismatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

