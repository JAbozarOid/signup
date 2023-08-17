package com.sample.auth.di.key

import com.sample.domain.enums.InfoType
import dagger.MapKey

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class InfoKey(val value: InfoType) {}
