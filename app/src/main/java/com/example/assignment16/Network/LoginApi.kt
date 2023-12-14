package com.example.assignment16.Network

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.assignment16.Responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi{
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password: String
    ) : LoginResponse
}