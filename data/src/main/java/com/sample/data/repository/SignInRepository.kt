package com.sample.data.repository

import com.sample.data.api.datastore.SignInRemoteDataSource
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val signInRemoteDataSource: SignInRemoteDataSource
) {

    val remote = signInRemoteDataSource

}