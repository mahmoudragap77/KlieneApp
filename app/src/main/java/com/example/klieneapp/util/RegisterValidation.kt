package com.example.klieneapp.util

sealed class RegisterValidation {
    object successValidations : RegisterValidation()
    data class failedValidations(val massage: String) :RegisterValidation()
}

data class RegisterFieldState(
    val email: RegisterValidation,
    val password: RegisterValidation
)