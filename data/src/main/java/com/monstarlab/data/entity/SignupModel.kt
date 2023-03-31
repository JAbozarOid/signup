package com.monstarlab.data.entity

import com.monstarlab.domain.entity.SignupResult

data class SignupModel(
    override val token: String
) : SignupResult