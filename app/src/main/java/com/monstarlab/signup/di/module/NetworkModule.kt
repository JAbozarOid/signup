package com.monstarlab.signup.di.module

import com.monstarlab.data.BuildConfig
import com.monstarlab.data.api.RetrofitHelper
import com.monstarlab.domain.enums.InfoType
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import retrofit2.Retrofit
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    fun provideRetrofit(
        infoMap: Map<InfoType, String>,
        interceptorsMap: Map<Int, @JvmSuppressWildcards Interceptor>
    ): Retrofit {

        return RetrofitHelper.createRetrofit(
            BuildConfig.BASE_URL,
            headerMap = infoMap.mapKeys {
                it.key.name.replace("_", "").lowercase(Locale.CANADA)
            },
            interceptorMap = interceptorsMap
        )

    }

}