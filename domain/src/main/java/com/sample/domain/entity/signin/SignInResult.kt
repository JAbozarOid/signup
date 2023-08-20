package com.sample.domain.entity.signin

import java.io.Serializable

interface SignInResult : Serializable {
    val id: Int
    val name: String
    val username: String
    val email: String
}