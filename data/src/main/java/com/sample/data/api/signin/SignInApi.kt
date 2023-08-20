package com.sample.data.api.signin

import com.sample.data.entity.signin.SignInResultModel
import com.sample.domain.entity.signin.SignInResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SignInApi {

    @GET("/users")
    suspend fun getSignIn(@Query("username") username: String): Response<List<SignInResultModel>>
}