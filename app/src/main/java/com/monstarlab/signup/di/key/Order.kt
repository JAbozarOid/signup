package com.monstarlab.signup.di.key

import dagger.MapKey

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Order(val value: Int) {}