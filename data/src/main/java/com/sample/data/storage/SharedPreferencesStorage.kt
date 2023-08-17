package com.sample.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.sample.domain.enums.StorageKey
import com.sample.domain.storage.Storage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor() : Storage {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("APP_CACHE", Context.MODE_PRIVATE)
    }


    override fun save(key: StorageKey, value: String) {
        sharedPreferences.edit().putString(key.name, value).apply()
    }

    override fun get(key: StorageKey): String? {
        return sharedPreferences.getString(key.name, null)
    }

    override fun contains(key: StorageKey): Boolean = sharedPreferences.contains(key.name)


    override fun truncate() {
        sharedPreferences.edit().clear().apply()
    }
}