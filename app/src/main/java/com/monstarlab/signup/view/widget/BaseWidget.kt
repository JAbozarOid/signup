package com.monstarlab.signup.view.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout

abstract class BaseWidget(context: Context, attr: AttributeSet, val viewId: Int) :
    LinearLayout(context, attr) {

    var inflater: LayoutInflater
    lateinit var view: View

    private fun initWidget(context: Context, attr: AttributeSet) {
        view = inflater.inflate(viewId, this)
        initLayout(context, attr)
    }

    abstract fun initLayout(context: Context, attr: AttributeSet)


    protected fun hideKeyboard() {
        val inm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    init {
        this.orientation = VERTICAL
        inflater = LayoutInflater.from(context)
        initWidget(context, attr)
    }

}