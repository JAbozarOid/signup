package com.sample.data.entity.signup

import com.sample.domain.entity.signup.SignupModel

data class SignupDataModel(
    override var email: String = "", override var password: String = ""
) : SignupModel