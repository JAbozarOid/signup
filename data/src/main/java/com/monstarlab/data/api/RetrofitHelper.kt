package com.monstarlab.data.api

import com.monstarlab.data.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private val loggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun createRetrofit(
        baseUrl: String,
        timeOutInSecond: Long = 45,
        interceptorMap: Map<Int, Interceptor> = emptyMap(),
        headerMap: Map<String, String> = emptyMap(),
        certPinner: CertificatePinner? = null
    ): Retrofit = Retrofit
        .Builder()
        .client(createClient(timeOutInSecond, interceptorMap, headerMap, certPinner))
        .baseUrl(baseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private fun createClient(
        timeOutInSecond: Long,
        interceptors: Map<Int, Interceptor>,
        headers: Map<String, String>,
        certPinner: CertificatePinner?
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.writeTimeout(timeOutInSecond, TimeUnit.SECONDS)
            .readTimeout(timeOutInSecond, TimeUnit.SECONDS)
            .connectTimeout(timeOutInSecond, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, 5, TimeUnit.SECONDS))
            .retryOnConnectionFailure(true)

        if (interceptors.isNotEmpty()) {
            val treeMap: TreeMap<Int, Interceptor> = TreeMap<Int, Interceptor>(interceptors)
            for (interceptor in treeMap.values) {
                okHttpClientBuilder.addInterceptor(interceptor)
            }
        }
        if (headers.isNotEmpty()) {
            okHttpClientBuilder.addInterceptor { chain: Interceptor.Chain ->
                val req = chain.request().newBuilder()
                for ((key, value) in headers.entries) {
                    req.addHeader(key, value)
                }
                chain.proceed(req.build())
            }
        }
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        //handle ssl pinning
        certPinner?.let {
            okHttpClientBuilder.certificatePinner(it)
        }
        return okHttpClientBuilder.build()
    }

}