package com.monstarlab.data.repository

import com.monstarlab.data.api.SignupApi
import com.monstarlab.domain.repository.ISignupRepository
import javax.inject.Inject

class SingupRepository @Inject constructor(val signupApi: SignupApi) : ISignupRepository {

    override suspend fun postSignupData(email: String, password: String): String {
        return signupApi.postSignup()
    }
}