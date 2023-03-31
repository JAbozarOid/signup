package com.monstarlab.signup.util

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.monstarlab.signup.view.widget.snack.SnackConfig
import com.monstarlab.signup.view.widget.snack.SnackStatus
import com.google.android.material.snackbar.Snackbar
import java.util.*

fun View.show() {
    visibility = VISIBLE
}

fun View.gone() {
    visibility = GONE
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.handleVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.invisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Date.dateToString(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.time
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return "$year/$month/$day"
}

fun Snackbar.setSnackColor(status: SnackStatus) {
    this.view.setBackgroundColor(this.context.resources.getColor(status.backgroundColor))
    this.setActionTextColor(this.context.resources.getColor(status.textColor))
    this.setTextColor(this.context.resources.getColor(status.textColor))
}

fun Snackbar.reset() {
    this.setAction(null, null)
}

fun Snackbar.setSnackConfig(config: SnackConfig) {
    this.setText(config.message)
    config.action?.let {
        this.setAction(it.first, it.second)
    }
    this.animationMode = Snackbar.ANIMATION_MODE_FADE
}

fun Boolean?.isNotNullOrFalse() = this != null && this



