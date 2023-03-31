package com.monstarlab.data.util

import androidx.datastore.preferences.core.stringPreferencesKey
import com.monstarlab.domain.enums.StorageKey


fun StorageKey.getStringKey() = stringPreferencesKey(this.name)