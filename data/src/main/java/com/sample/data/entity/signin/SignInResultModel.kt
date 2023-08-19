package com.sample.data.entity.signin

import com.sample.domain.entity.signin.SignInResult

data class SignInResultModel(
    override val id: Int,
    override val name: String,
    override val username: String,
    override val email: String
) : SignInResult
