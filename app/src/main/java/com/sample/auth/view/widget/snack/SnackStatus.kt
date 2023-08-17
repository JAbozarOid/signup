package com.sample.auth.view.widget.snack

import androidx.annotation.ColorRes
import com.sample.auth.R

enum class SnackStatus(@ColorRes var backgroundColor: Int, @ColorRes var textColor: Int) {
    DEFAULT(R.color.white2, R.color.black),
    RED(R.color.error, R.color.white2),
    GREEN(R.color.success, R.color.white2),
    SURFACE(R.color.surface, R.color.black)
}