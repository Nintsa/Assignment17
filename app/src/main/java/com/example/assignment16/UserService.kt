package com.example.assignment16

import com.example.assignment16.Responses.LoginResponse
import com.example.assignment16.requests.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService{
    @POST("login")
    fun login(@Body request: LoginRequest): LoginResponse
}