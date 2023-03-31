package com.monstarlab.signup.data.service.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}