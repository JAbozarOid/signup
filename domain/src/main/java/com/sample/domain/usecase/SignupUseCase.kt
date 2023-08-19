package com.sample.domain.usecase

import com.sample.domain.entity.signup.SignupModel
import com.sample.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class SignupUseCase @Inject constructor(val authRepository: AuthRepository) : BaseUseCase<SignupModel, Response<String>>() {


    override suspend fun onExecute(param: SignupModel) : Response<String> {
       return authRepository.postSignupData(email = param.email, password = param.password)
    }
}