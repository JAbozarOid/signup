package com.monstarlab.domain.repository

import com.monstarlab.domain.entity.SignupResult

interface ISignupRepository {

    suspend fun postSignupData(email : String, password : String) : SignupResult
}