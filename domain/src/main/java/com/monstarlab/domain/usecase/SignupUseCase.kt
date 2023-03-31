package com.monstarlab.domain.usecase

import com.monstarlab.domain.entity.SignupResult
import com.monstarlab.domain.repository.ISignupRepository

class SignupUseCase constructor(val iSignupRepository: ISignupRepository) : BaseUseCase<Unit, SignupResult>() {


    override suspend fun onExecute(param: Unit) : SignupResult{
       return iSignupRepository.postSignupData(email = "abozar.raghibdoust@gmail.com", password = "ar13650319")
    }
}