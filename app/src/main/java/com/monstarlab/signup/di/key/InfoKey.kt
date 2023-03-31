package com.monstarlab.signup.di.key

import com.monstarlab.domain.enums.InfoType
import dagger.MapKey

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class InfoKey(val value: InfoType) {}
