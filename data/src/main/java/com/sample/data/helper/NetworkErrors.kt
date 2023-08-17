package com.sample.data.helper

enum class NetworkErrors(val code: Int) {
    TIME_OUT(-1),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    SERVER_ERROR(500),
    PARSE_ERROR(400)
}