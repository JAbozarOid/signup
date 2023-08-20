package com.sample.data.util

data class GenericResponse<T>(
    var results: List<T>
)