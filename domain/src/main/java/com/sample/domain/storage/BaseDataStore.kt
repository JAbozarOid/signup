package com.sample.domain.storage

import com.sample.domain.enums.StorageKey
import kotlinx.coroutines.flow.Flow

interface BaseDataStore {

    //region Store
    suspend fun save(key: StorageKey, value: String)
    //endregion

    //region Restore
    suspend fun getString(key: StorageKey): Flow<String>
    //endregion

    //region Truncate
    suspend fun truncate()
    //endregion

}