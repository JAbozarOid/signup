package com.sample.auth.util

import android.content.Context

object ResourceUtil {

    private val resourceCacheId = HashMap<String, Int>()

    fun getIdByName(context: Context, name: String): Int {
        if (resourceCacheId.containsKey(name))
            return resourceCacheId[name]!!

        val id = context.resources.getIdentifier(name, "id", context.packageName)
        resourceCacheId[name] = id
        return id
    }

}