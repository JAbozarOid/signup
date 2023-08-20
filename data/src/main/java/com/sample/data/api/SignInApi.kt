package com.sample.data.api

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.sample.data.entity.signin.SignInResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SignInApi {

    @GET("/users")
    suspend fun getSignIn(@Query("username") username: String): Response<List<SignInResultModel>>
}