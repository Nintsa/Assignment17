package com.example.assignment16.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment16.network.Resource
import com.example.assignment16.network.request.LoginRequest
import com.example.assignment16.network.response.LoginResponse
import com.example.assignment16.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginResponse: MutableStateFlow<Resource<LoginResponse>> =
        MutableStateFlow(Resource.Loading)

    val loginResponse: StateFlow<Resource<LoginResponse>>
        get() = _loginResponse.asStateFlow()

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _loginResponse.emit(Resource.Loading)
            _loginResponse.emit(repository.login(LoginRequest(email, password)))
        }
    }

    fun resetState() {
        viewModelScope.launch {
            _loginResponse.emit(Resource.Loading)
        }
    }
}
