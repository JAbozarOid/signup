package com.sample.data.repository

import com.sample.data.api.AuthApi
import com.sample.data.api.SignInApi
import com.sample.data.entity.signin.SignInResultModel
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