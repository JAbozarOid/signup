package com.sample.auth.view.widget.simplelistwidget

import android.graphics.drawable.Drawable

data class SimpleListWidgetModel(
    val title: String?,
    val icon: Drawable?,
    val onClick: (() -> Unit)? = null
) {
}