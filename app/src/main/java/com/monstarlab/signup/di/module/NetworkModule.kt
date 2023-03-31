package com.monstarlab.signup.di.module

import com.monstarlab.data.BuildConfig
import com.monstarlab.data.api.RetrofitHelper
import com.monstarlab.data.api.SignupApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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
            "https://truecaller.blog/2018/01/22/",
            //interceptorMap = interceptorsMap
        )
    }

    @Provides
    fun provideSignupApi(retroFit: Retrofit): SignupApi {
        return retroFit.create(SignupApi::class.java)
    }
}