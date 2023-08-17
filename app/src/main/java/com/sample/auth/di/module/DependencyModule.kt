package com.sample.auth.di.module

import com.sample.data.repository.ResourceTranslator
import com.sample.data.storage.SharedPreferencesStorage
import com.sample.data.storage.dataStore.DataStore
import com.sample.domain.repository.Translator
import com.sample.domain.storage.BaseDataStore
import com.sample.domain.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DependencyModule {

    @Binds
    abstract fun bindStorage(storage: SharedPreferencesStorage) : Storage

    @Binds
    abstract fun bindDataStore(dataStore: DataStore) : BaseDataStore

    @Binds
    @Singleton
    abstract fun bindTranslator(translator: ResourceTranslator) : Translator

}