package com.monstarlab.data.entity.exception

open class CustomException(override val message: String, val code: String) : Exception() {
}