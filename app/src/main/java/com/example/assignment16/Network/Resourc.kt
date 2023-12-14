package com.example.assignment16.Network

import okhttp3.ResponseBody

//we use this selaed class to wrap our api responses and handle success and failure properly

//restricted class hierarchies that provide more control over inheritance
sealed class Resource<out T> {
    data class Success<out T>(val value: T): Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}

