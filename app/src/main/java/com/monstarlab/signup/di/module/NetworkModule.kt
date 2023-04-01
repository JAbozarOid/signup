package com.monstarlab.signup.di.module

import com.monstarlab.data.api.RetrofitHelper
import com.monstarlab.data.api.SignupApi
import com.monstarlab.signup.constants.AppConstants.BASE_UEL
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