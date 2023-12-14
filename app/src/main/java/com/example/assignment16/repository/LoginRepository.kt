package com.example.assignment16.repository

import com.example.assignment16.Network.LoginApi

class LoginRepository(private val api: LoginApi) : BaseRepository() {
    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(username, password)
    }

}