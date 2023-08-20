package com.sample.data.repository

import com.sample.data.api.auth.AuthApi
import com.sample.data.api.signin.SignInApi
import com.sample.domain.entity.signin.SignInResult
import com.sample.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AppAuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val signInApi: SignInApi
) : AuthRepository {

    override suspend fun postSignupData(
        email: String,
        password: String
    ): Response<String> {
        return authApi.postSignup(email, password)
    }

    override suspend fun getSignInData(username: String) : Response<List<SignInResult>> {
        return signInApi.getSignIn(username = username) as Response<List<SignInResult>>
    }
}