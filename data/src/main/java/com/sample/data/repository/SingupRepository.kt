package com.sample.data.repository

import com.sample.data.api.SignupApi
import com.sample.domain.repository.ISignupRepository
import retrofit2.Response
import javax.inject.Inject

class SingupRepository @Inject constructor(val signupApi: SignupApi) : ISignupRepository {

    override suspend fun postSignupData(
        email: String,
        password: String
    ): Response<String> {
        return signupApi.postSignup(email, password)
    }
}