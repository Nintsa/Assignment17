package com.example.assignment16.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment16.RegisterViewModel
import com.example.assignment16.home.HomeViewModel
import com.example.assignment16.login.LoginViewModel
import com.example.assignment16.repository.LoginRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(LoginRepository) as T

            modelClass.isAssignableFrom(RegisterViewModel::class.java) ->
                RegisterViewModel(LoginRepository) as T

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel() as T

            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}
