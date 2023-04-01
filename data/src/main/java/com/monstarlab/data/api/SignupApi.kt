package com.monstarlab.data.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignupApi {

    @FormUrlEncoded
    @POST("/signup")
    suspend fun postSignup(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<String>
}