package com.example.assignment16

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment16.network.Resource
import com.example.assignment16.network.request.LoginRequest
import com.example.assignment16.network.response.RegisterResponse
import com.example.assignment16.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _registerResponse: MutableStateFlow<Resource<RegisterResponse>> =
        MutableStateFlow(Resource.Loading)

    val registerResponse: StateFlow<Resource<RegisterResponse>>
        get() = _registerResponse.asStateFlow()

    fun register(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _registerResponse.value = Resource.Loading
            _registerResponse.value = loginRepository.register(LoginRequest(email, password))
        }
    }
}
