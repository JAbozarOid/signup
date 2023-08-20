package com.sample.auth.di.module

import com.sample.auth.constants.AppConstants.BASE_URL_AUTH
import com.sample.data.api.signin.SignInApi
import com.sample.data.api.todo.TodoApi
import com.sample.data.util.LiveDataCallAdapterFactory
import com.sample.data.util.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitNetworkModule {

    @Provides
    @Singleton
    fun provideGlobalOkhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okhttpBuilder =
            OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS).addInterceptor(interceptor)
        return okhttpBuilder.build()
    }

    @CloudRetrofit
    @Provides
    @Singleton
    fun provideOkhttp(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okhttpBuilder =
            OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @CloudRetrofit
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        @CloudRetrofit okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .client(okHttpClient)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideSignInApi(
        @CloudRetrofit retrofit: Retrofit
    ): SignInApi {
        return retrofit.create(SignInApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTodoApi(
        @CloudRetrofit retrofit: Retrofit
    ): TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CloudRetrofit

