package com.monstarlab.domain.repository

interface ISignupRepository {

    suspend fun postSignupData(email : String, password : String) : String
}