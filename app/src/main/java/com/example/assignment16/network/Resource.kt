package com.example.assignment16.network

sealed class Resource<out T> {

    data object Loading : Resource<Nothing>()

    data class Success<out T>(val value: T) : Resource<T>()

    data object Failure : Resource<Nothing>()
}
