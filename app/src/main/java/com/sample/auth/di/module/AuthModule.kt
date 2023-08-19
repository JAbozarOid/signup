package com.sample.auth.di.module

import com.sample.data.api.RetrofitHelper
import com.sample.auth.constants.AppConstants.BASE_URL
import com.sample.auth.constants.AppConstants.BASE_URL_AUTH
import com.sample.data.api.SignInApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import java.util.*
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @AUTHRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(
        //interceptorsMap: Map<Int, @JvmSuppressWildcards Interceptor>
    ): Retrofit {
        return RetrofitHelper.createRetrofit(
            BASE_URL_AUTH,
            //interceptorMap = interceptorsMap
        )
    }

    @Provides
    @Singleton
    fun provideSignInApi(
        @AUTHRetrofit retrofit: Retrofit
    ): SignInApi {
        return retrofit.create(SignInApi::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AUTHRetrofit