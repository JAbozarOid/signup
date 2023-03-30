package com.monstarlab.signup.view.widget.button

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.monstarlab.signup.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CustomButtonWidget(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private lateinit var mButton: Button
    private lateinit var mProgressbar: ProgressBar

    private var mColorTextDisabled: Int? = null
    private var mColorTextEnabled: Int? = null
    private var mColorTextLoading: Int? = null
    private var mColorButtonDisabled: Int? = null
    private var mColorButtonEnabled: Int? = null

    private var mAttributes: TypedArray

    private var mButtonState = CustomButtonState.Default

    init {
        inflate(context, R.layout.widget_button_custom, this)
        mAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomButtonWidget)
        initViews()
        setAttributes()
        isClickable = true
    }

    override fun setOnClickListener(l: OnClickListener?) {
        mButton.setOnClickListener(l)
        super.setOnClickListener(l)
    }

    private fun getStyles() {
        mColorTextEnabled = ContextCompat.getColor(context, R.color.app_black)
        mColorTextLoading = ContextCompat.getColor(context, R.color.auth_black24)
//        mColorButtonEnabled = ContextCompat.getColor(context, R.color.auth_green)
        mColorButtonEnabled = ContextCompat.getColor(context, R.color.auth_idle)
        mColorTextDisabled = ContextCompat.getColor(context, R.color.auth_blue_gray54)
        mColorButtonDisabled = ContextCompat.getColor(context, R.color.auth_gray12)
    }

    /**
     * init all the views from the row.xml
     */
    private fun initViews() {
        getStyles()
        mProgressbar = findViewById(R.id.custom_button_progress)
        mButton = findViewById(R.id.custom_button_btn)
    }

    private fun setAttributes() {
        mButton.text = mAttributes.getString(R.styleable.CustomButtonWidget_text)
    }

    fun setToLoading() {
        changeButtonState(CustomButtonState.Loading)
    }

    fun setToDefault() {
        changeButtonState(CustomButtonState.Default)
    }

    fun setToDisable() {
        changeButtonState(CustomButtonState.Disable)
    }

    private fun changeButtonState(customButtonState: CustomButtonState) {
        mButtonState = customButtonState
        when (mButtonState) {
            CustomButtonState.Loading -> {
                mButton.setTextColor(mColorTextLoading!!)
                mButton.setBackgroundColor(mColorButtonEnabled!!)
                mButton.isEnabled = false
                mProgressbar.visibility = View.VISIBLE
            }
            CustomButtonState.Default -> {
                mButton.setTextColor(mColorTextEnabled!!)
                mButton.setBackgroundColor(mColorButtonEnabled!!)
                mButton.isEnabled = true
                mProgressbar.visibility = View.GONE
            }
            CustomButtonState.Disable -> {
                mButton.setTextColor(mColorTextDisabled!!)
                mButton.setBackgroundColor(mColorButtonDisabled!!)
                mButton.isEnabled = false
                mProgressbar.visibility = View.GONE
            }
        }
    }

    fun <T> debounce(
        delayMs: Long = 1000L,
        coroutineContext: CoroutineContext,
        f: (T) -> Unit
    ): (T) -> Unit {
        var debounceJob: Job? = null
        return { param: T ->
            if (debounceJob?.isCompleted != false) {
                debounceJob = CoroutineScope(coroutineContext).launch {
                    delay(delayMs)
                    f(param)
                }
            }
        }
    }
}

enum class CustomButtonState {
    Loading, Default, Disable
}















