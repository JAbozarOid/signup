package com.sample.auth.extension

import android.widget.EditText

/**
 * makes edit text behave as text view
 */
fun EditText.setEditTextAsTextView() {
    isCursorVisible = false
    isLongClickable = false
    isClickable = false
    isFocusable = false
    isSelected = false
    keyListener = null
    setBackgroundResource(android.R.color.transparent)
}