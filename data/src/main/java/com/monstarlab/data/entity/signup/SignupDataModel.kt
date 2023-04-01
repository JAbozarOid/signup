package com.monstarlab.data.entity.signup

import com.monstarlab.domain.entity.SignupModel

data class SignupDataModel(
    override var email: String = "", override var password: String = ""
) : SignupModel