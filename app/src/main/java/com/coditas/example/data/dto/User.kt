package com.coditas.example.data.dto

data class User(
    val userId: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val phoneNo: String? = null,
    var password: String? = null,
    val accessToken: String? = null
)
