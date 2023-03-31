package com.monstarlab.signup.di.module

import com.monstarlab.data.repository.ResourceTranslator
import com.monstarlab.data.storage.SharedPreferencesStorage
import com.monstarlab.data.storage.dataStore.DataStore
import com.monstarlab.domain.repository.Translator
import com.monstarlab.domain.storage.BaseDataStore
import com.monstarlab.domain.storage.Storage
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