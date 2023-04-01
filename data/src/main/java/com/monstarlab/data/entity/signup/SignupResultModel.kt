package com.monstarlab.data.entity.signup

import com.monstarlab.domain.entity.SignupResult

data class SignupResultModel(override val token: String) : SignupResult
