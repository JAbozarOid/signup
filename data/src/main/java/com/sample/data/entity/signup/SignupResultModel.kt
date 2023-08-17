package com.sample.data.entity.signup

import com.sample.domain.entity.SignupResult

data class SignupResultModel(override val token: String) : SignupResult
