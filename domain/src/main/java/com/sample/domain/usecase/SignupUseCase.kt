package com.sample.domain.usecase

import com.sample.domain.entity.SignupModel
import com.sample.domain.repository.ISignupRepository
import retrofit2.Response
import javax.inject.Inject

class SignupUseCase @Inject constructor(val iSignupRepository: ISignupRepository) : BaseUseCase<SignupModel, Response<String>>() {


    override suspend fun onExecute(param: SignupModel) : Response<String> {
       return iSignupRepository.postSignupData(email = param.email, password = param.password)
    }
}