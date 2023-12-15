package com.example.assignment16.network

import com.example.assignment16.network.request.LoginRequest
import com.example.assignment16.network.response.LoginResponse
import com.example.assignment16.network.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: LoginRequest): RegisterResponse
}
