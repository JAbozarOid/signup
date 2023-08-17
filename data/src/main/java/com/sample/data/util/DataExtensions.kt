package com.sample.data.util

import androidx.datastore.preferences.core.stringPreferencesKey
import com.sample.domain.enums.StorageKey


fun StorageKey.getStringKey() = stringPreferencesKey(this.name)