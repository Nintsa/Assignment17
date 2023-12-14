package com.example.assignment16.repository


import android.net.http.HttpException
import android.os.Build
import android.os.ext.SdkExtensions
import com.example.assignment16.Network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                        Build.VERSION_CODES.S) >= 7) {
                    when (throwable) {
                        is HttpException -> {
                            Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                        }
                        else -> {
                            Resource.Failure(true, null, null )
                        }
                    }
                } else {
                    Resource.Failure(true, null, null )//idk
                }
            }
        }
    }
}
