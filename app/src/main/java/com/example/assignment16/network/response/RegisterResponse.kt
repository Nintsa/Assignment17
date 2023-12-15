package com.example.assignment16.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    val id: Int,
    val token: String
)
