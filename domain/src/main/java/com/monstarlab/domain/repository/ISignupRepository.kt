package com.monstarlab.domain.repository

import retrofit2.Response

interface ISignupRepository {

    suspend fun postSignupData(email : String, password : String) : Response<String>
}