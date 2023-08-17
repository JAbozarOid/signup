package com.sample.domain.storage

import com.sample.domain.enums.StorageKey

interface Storage {

    fun save(key: StorageKey, value: String)

    fun save(key: StorageKey, value: Boolean) {
        save(key, value.toString())
    }

    fun get(key: StorageKey): String?

    fun getBoolean(key: StorageKey): Boolean = try {
        get(key).toBoolean()
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    fun contains(key: StorageKey): Boolean

    fun truncate()

}