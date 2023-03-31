package com.monstarlab.data.api

import retrofit2.http.GET

interface SignupApi {

    @GET("life-as-an-android-engineer")
    suspend fun postSignup(
        //@Field("email") email: String,
        // @Field("password") password: String,
    ): String
}