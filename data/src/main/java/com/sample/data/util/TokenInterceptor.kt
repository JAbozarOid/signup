package com.sample.data.util

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .url(originalHttpUrl)
        val requestHeader = requestBuilder.build()
        return chain.proceed(requestHeader)
    }

}