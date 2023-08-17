package com.sample.auth.di.module

import com.sample.data.api.RetrofitHelper
import com.sample.data.api.SignupApi
import com.sample.auth.constants.AppConstants.BASE_UEL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(
        //interceptorsMap: Map<Int, @JvmSuppressWildcards Interceptor>
    ): Retrofit {
        return RetrofitHelper.createRetrofit(
            BASE_UEL,
            //interceptorMap = interceptorsMap
        )
    }

    @Provides
    fun provideSignupApi(retroFit: Retrofit): SignupApi {
        return retroFit.create(SignupApi::class.java)
    }
}