package com.example.klieneapp.util

import android.util.Patterns

fun validateEmail(email: String): RegisterValidation {
    if (email.isEmpty())
        return RegisterValidation.failedValidations("Email cannot be empty")
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.failedValidations("Wrong email format")
    return RegisterValidation.successValidations

}

fun validatePassword(password: String): RegisterValidation {
    if (password.isEmpty())
        return RegisterValidation.failedValidations("Password cannot be empty")
    if (password.length < 6)
        return RegisterValidation.failedValidations("Password should contains 6 char")
    return RegisterValidation.successValidations
}