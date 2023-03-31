package com.monstarlab.domain.usecase

import com.monstarlab.domain.repository.ISignupRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(val iSignupRepository: ISignupRepository) : BaseUseCase<Unit, String>() {


    override suspend fun onExecute(param: Unit) : String{
       return iSignupRepository.postSignupData(email = "abozar.raghibdoust@gmail.com", password = "ar13650319")
    }
}