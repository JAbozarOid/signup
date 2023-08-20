package com.sample.data.entity.signin

import com.google.gson.annotations.SerializedName
import com.sample.domain.entity.signin.SignInResult

data class SignInResultModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
)
