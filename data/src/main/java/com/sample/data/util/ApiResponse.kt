package com.sample.data.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.sample.data.constants.Constants.ERROR_GENERAL_ERROR
import com.sample.data.constants.Constants.ERROR_MODE_NOT_ADVANCED
import com.sample.data.constants.Constants.ERROR_NO_NETWORK
import com.sample.data.constants.Constants.ERROR_NO_SERVER
import com.sample.data.constants.Constants.ERROR_TIME_OUT
import retrofit2.Response


sealed class ApiResponse<T> {


    companion object {
        fun <T> create(error: Throwable): ErrorTryAgain<T> {
            var errorMessage = error.message
            errorMessage = when {
                errorMessage?.contains("timeout") == true -> {
                    ERROR_TIME_OUT
                }
                errorMessage?.contains(ERROR_NO_SERVER, true) == true -> {
                    ERROR_NO_NETWORK
                }
                errorMessage?.contains(ERROR_MODE_NOT_ADVANCED) == true -> {
                    ERROR_MODE_NOT_ADVANCED
                }
                else -> {
                    ERROR_GENERAL_ERROR
        }
    }
    return ErrorTryAgain(
    errorMessage = errorMessage
    )
}

fun <T> create(response: Response<T>): ApiResponse<T> {
            if (response.isSuccessful) {
                val body = response.body()
                return Success(data = body!!)
            } else {
                val gson = Gson()
                val type = object : TypeToken<JsonObject>() {}.type
                val errorResponse: JsonObject? =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                val msg = errorResponse?.asJsonObject?.get("error")?.toString() ?: ""
                val code = errorResponse?.asJsonObject?.get("code").toString()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    errorResponse?.asJsonObject?.get("error")?.toString() ?: ""
                } else {
                    msg
                }
                return Error(
                    errorMessage = errorMsg,
                    httpCode = response.code().toString(),
                    code = code,
                    url = ""
                )
            }
        }
    }

    class Loading<T> : ApiResponse<T>()
    class ErrorTryAgain<T>(val errorMessage: String) : ApiResponse<T>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(
        var errorMessage: String,
        val httpCode: String = "",
        val code: String = "",
        val url: String = ""
    ) : ApiResponse<T>()
}
