package com.sample.data.api.datastore

import com.sample.data.api.signin.SignInApi
import com.sample.data.util.NetworkUtil.safeApiCall
import javax.inject.Inject

class SignInRemoteDataSource @Inject constructor(
    private val signInApi: SignInApi
) {

    suspend fun getSignInData(
        username: String,
    ) = safeApiCall {
        signInApi.getSignIn(username = username)
    }
}