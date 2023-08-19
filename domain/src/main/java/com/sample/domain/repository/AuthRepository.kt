package com.sample.domain.repository

import com.sample.domain.entity.signin.SignInResult
import retrofit2.Response

interface AuthRepository {
    suspend fun postSignupData(email: String, password: String): Response<String>
}