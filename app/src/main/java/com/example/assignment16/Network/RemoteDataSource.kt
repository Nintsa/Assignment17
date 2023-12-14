package com.example.assignment16.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create()).build().create(api)
    }

    companion object {
        private const val BASE_URL = "https://reqres.in/api/"
    }
}