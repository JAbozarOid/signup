package com.sample.data.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

object ResourceUtil {

    private val cache = hashMapOf<String, Int>()

    fun getStringByName(context: Context, name: String): String {
        val id = context.resources.getIdentifier(name, "string", context.packageName)
        var value: String? = null
        try {
            value = context.getString(id) ?: null
        } catch (e: Exception) {
        }
        return value ?: name
    }

    fun getIdByName(context: Context, name: String): Int {
        if (cache.containsKey(name)) return cache[name]!!
        if (name.contains("http")) return 0
        return context.resources.getIdentifier(name, "id", context.packageName).also {
            cache[name] = it
        }
    }

    fun getNameById(context: Context, id: Int): String {
        if (id == 0) return ""
        return try {
            context.resources.getResourceEntryName(id)
        } catch (e: Exception) {
            ""
        }
    }

    fun getDrawableIdByName(context: Context, name: String): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    fun getDrawableByName(context: Context, name: String): Drawable? {
        val id = getDrawableIdByName(context, name)
        return try {
            ContextCompat.getDrawable(context, id)
        } catch (e: Exception) {
            null
        }
    }


}