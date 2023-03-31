package com.monstarlab.data.helper

import retrofit2.HttpException
import java.net.SocketTimeoutException


open class ResponseHandler {


    /*fun <T : Any> handleSuccess(data: T): Response<T> {
        return Response.success(data)
    }*/

    /*fun <T : Any> handleException(e: Exception): Response<T> {
        return when (e) {
            is HttpException -> Response.error(null, getErrorMessage(e.code()))
            is SocketTimeoutException -> Response.error(
                null,  getErrorMessage(ApiErrorCodes.TIMEOUT.code)
            )
            else -> Response.error(null, getErrorMessage(Int.MAX_VALUE))
        }
    }*/

    /*private fun getErrorMessage(code: Int): String {
        return when (code) {
            ApiErrorCodes.TIMEOUT.code -> "Timeout"
            ApiErrorCodes.UNAUTHORIZED.code -> "Unauthorised"
            ApiErrorCodes.NOT_FOUND.code -> "Not found"
            else -> "Something went wrong"
        }
    }*/

}