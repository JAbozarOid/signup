package com.monstarlab.data.api

import com.monstarlab.data.entity.SignupModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignupApi {

    @FormUrlEncoded
    @POST("v1/signup")
    suspend fun postSignup(
        @Field("email") email: String,
        @Field("password") password: String,
    ): SignupModel
}