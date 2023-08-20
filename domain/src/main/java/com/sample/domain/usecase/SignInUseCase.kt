package com.sample.domain.usecase

import com.sample.domain.entity.signin.SignInModel
import com.sample.domain.entity.signin.SignInResult
import com.sample.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) :
    BaseUseCase<SignInModel, Response<List<SignInResult>>>() {


    override suspend fun onExecute(param: SignInModel): Response<List<SignInResult>> {
        return authRepository.getSignInData(username = param.username)
    }
}