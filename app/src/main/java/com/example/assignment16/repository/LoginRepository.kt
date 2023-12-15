package com.example.assignment16.repository

import com.example.assignment16.network.NetworkClient
import com.example.assignment16.network.Resource
import com.example.assignment16.network.UserService
import com.example.assignment16.network.request.LoginRequest
import com.example.assignment16.network.response.LoginResponse
import com.example.assignment16.network.response.RegisterResponse
import java.lang.Exception

object LoginRepository {

    private val service = NetworkClient.getRetrofit().create(UserService::class.java)

    suspend fun login(
        request: LoginRequest
    ): Resource<LoginResponse> {
        return try {
            val result = service.login(request)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure
        }
    }

    suspend fun register(
        request: LoginRequest
    ): Resource<RegisterResponse> {
        return try {
            val result = service.register(request)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure
        }
    }
}
