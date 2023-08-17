package com.sample.data.repository

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.sample.data.util.ResourceUtil
import com.sample.domain.repository.Translator
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceTranslator @Inject constructor(@ApplicationContext val context: Context) :
    Translator {

    override fun getString(id: Int): String {
        return context.resources.getString(id) ?: ""
    }

    override fun getDrawable(id: Int): Any {
        return ContextCompat.getDrawable(context, id) as Drawable
    }

    override fun getResourceName(id: Int): String {
        return ResourceUtil.getNameById(context, id)
    }

    override fun getResourceId(name: String): Int {
        return ResourceUtil.getIdByName(context, name)
    }

    override fun getDrawableByName(name: String): Any {
        return ResourceUtil.getDrawableByName(context, name) as Drawable
    }

    override fun getDrawableId(name: String): Int {
        return try {
            ResourceUtil.getDrawableIdByName(context, name)
        } catch (e: Exception) {
            0
        }
    }
}