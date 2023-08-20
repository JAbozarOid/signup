package com.sample.auth.di.module

import com.sample.data.repository.AppAuthRepository
import com.sample.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSignupRepository(signupRepository: AppAuthRepository) : AuthRepository
}