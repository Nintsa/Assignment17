package com.example.assignment16.login

import android.app.Person
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment16.Network.Resource
import com.example.assignment16.Responses.LoginResponse
import com.example.assignment16.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
): ViewModel() {
    private val _loginResponse : MutableStateFlow<Resource<LoginResponse>> = MutableStateFlow()
    )
    val loginResponse: StateFlow<Resource<LoginResponse>>
        get() = _loginResponse

fun login(
    username: String,
    password: String
) = viewModelScope.launch {
    _loginResponse.value = repository.login(username, password)
}
}