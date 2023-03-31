package com.monstarlab.data.helper

import com.monstarlab.data.entity.exception.CustomException
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.net.SocketTimeoutException

object ApiResponseParser {
    fun <T> parse(response: ApiResponse<T>): T {
        try {

            response.error?.let {
                throw CustomException(it.message ?: "Api Error", it.code ?: "-1")
            }

            return response.data!!

        } catch (e: Exception) {

            e.printStackTrace()

            if (e is CustomException || e.cause is CustomException) throw e

            if (e is HttpException || e.cause is HttpException) {

                e as HttpException

                throw CustomException(
                    getErrorMessage(e.code()),
                    e.code().toString()
                )

            }

            if (e is SocketTimeoutException || e.cause is SocketTimeoutException)
                throw CustomException(
                    getErrorMessage(NetworkErrors.TIME_OUT.code),
                    NetworkErrors.TIME_OUT.code.toString()
                )

            if (e is JsonSyntaxException)
                throw CustomException(
                    getErrorMessage(NetworkErrors.PARSE_ERROR.code),
                    NetworkErrors.PARSE_ERROR.code.toString()
                )

            throw CustomException(
                getErrorMessage(Integer.MAX_VALUE),
                Integer.MAX_VALUE.toString()
            )

        }

    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            NetworkErrors.TIME_OUT.code -> "check your connection please"
            NetworkErrors.SERVER_ERROR.code -> "server is not responding"
            NetworkErrors.NOT_FOUND.code -> "server is not found"
            NetworkErrors.PARSE_ERROR.code -> "could not parse response"
            NetworkErrors.UNAUTHORIZED.code -> "access denied"
            else -> "unknown error!"
        }
    }
}