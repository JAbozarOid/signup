package com.monstarlab.domain.repository

interface Translator {

    fun getString(id: Int): String

    fun getDrawable(id: Int): Any

    fun getResourceName(id: Int): String

    fun getResourceId(name: String): Int

    fun getDrawableByName(name: String): Any

    fun getDrawableId(name: String): Int

}