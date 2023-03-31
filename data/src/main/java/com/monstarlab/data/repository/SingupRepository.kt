package com.monstarlab.data.repository

import com.monstarlab.data.api.SignupApi
import com.monstarlab.data.entity.SignupModel
import com.monstarlab.domain.repository.ISignupRepository
import javax.inject.Inject

class SingupRepository @Inject constructor(val signupApi: SignupApi) : ISignupRepository {

    override suspend fun postSignupData(email: String, password: String): SignupModel {
        return signupApi.postSignup(email = email , password = password)
    }
}