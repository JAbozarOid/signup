package com.monstarlab.signup.di.module

import com.monstarlab.data.repository.SingupRepository
import com.monstarlab.data.storage.dataStore.DataStore
import com.monstarlab.domain.repository.ISignupRepository
import com.monstarlab.domain.storage.BaseDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDataStore(dataStore: SingupRepository) : ISignupRepository
}