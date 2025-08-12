package com.example.klieneapp.data

class User(
    val name: String,
    val email: String,
    val password: String,
    val imgPath: String?=""
) {

    constructor() : this("", "", "", "")
}