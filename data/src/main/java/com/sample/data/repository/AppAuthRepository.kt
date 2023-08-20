package com.sample.data.repository

import com.sample.data.api.AuthApi
import com.sample.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AppAuthRepository @Inject constructor(private val authApi: AuthApi) : AuthRepository {

    override suspend fun postSignupData(
        email: String,
        password: String
    ): Response<String> {
        return authApi.postSignup(email, password)
    }
}