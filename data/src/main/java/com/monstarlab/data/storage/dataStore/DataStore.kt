package com.monstarlab.data.storage.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit

import androidx.datastore.preferences.preferencesDataStore
import com.monstarlab.data.util.getStringKey
import com.monstarlab.domain.enums.StorageKey
import com.monstarlab.domain.storage.BaseDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject constructor() : BaseDataStore {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    private val NAME = "routineCache"

    private val Context.dataStore by preferencesDataStore(NAME)


    override suspend fun save(key: StorageKey, value: String) {
        context.dataStore.edit { pref ->
            pref[key.getStringKey()] = value
        }
    }

    override suspend fun getString(key: StorageKey): Flow<String> =
        context.dataStore.data.map { pref ->
            pref[key.getStringKey()].toString()
        }

    override suspend fun truncate() {
        context.dataStore.edit { pref ->
            pref.clear()
        }
    }

}