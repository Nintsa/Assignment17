package com.example.assignment16.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment16.login.LoginViewModel
import com.example.assignment16.repository.BaseRepository
import com.example.assignment16.repository.LoginRepository

class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as LoginRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}