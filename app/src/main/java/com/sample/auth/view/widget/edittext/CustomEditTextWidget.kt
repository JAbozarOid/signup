package com.sample.auth.view.widget.edittext

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.LiveData
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.sample.auth.R
import com.sample.auth.extension.setEditTextAsTextView
import com.sample.auth.util.AuthUtil
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.EMAIL
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.EMPTY
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.NONE
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.NUMBER
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.PASSWORD
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.SEARCH
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.TEXT
import com.sample.auth.view.widget.edittext.CustomEditTextWidgetInputType.Companion.TEXT_VIEW


class CustomEditTextWidget(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    // ui components
    private lateinit var mImageViewType: ImageView
    private lateinit var mViewVisibilityClick: View
    private lateinit var mImageViewPasswordVisibility: ImageView
    private lateinit var mImageViewValidation: ImageView
    private lateinit var mImageViewClose: ImageView
    private lateinit var mConstraintLayoutRoot: ConstraintLayout
    private lateinit var editTextInput: EditText
    private lateinit var mTextViewError: TextView
    private lateinit var mTextWatcher: TextWatcher
    private var mAttributes: TypedArray

    // ui res
    private var mColorSuccess: Int? = null
    private var mColorFailure: Int? = null
    private var mColorDefault: Int? = null
    private var mColorDefaultIcon: Int? = null
    private var mBackgroundSuccess: Drawable? = null
    private var mBackgroundFailure: Drawable? = null
    private var mSuccessStateIcon: Drawable? = null
    private var mShowPasswordIcon: Drawable? = null
    private var mHidePasswordIcon: Drawable? = null
    private var mFailureStateIcon: Drawable? = null

    // different state handling variables
    private var mShowValidation = false
    private var mIsPasswordVisible = false
    private var mShouldPasswordShow = true
    private var mIsPasswordVisibilityIconVisible = false
    private var mInputState: InputState = InputState.IS_DEFAULT
    private var mInputType: Int = NONE

    var text = ""

    private val _textFocusLiveData: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val textFocusLiveData: LiveData<String> get() = _textFocusLiveData

    private val _textLiveData: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val textLiveData: LiveData<String> get() = _textLiveData

    private var numberInputType =
        InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED

    init {
        initStyles()
        inflate(context, R.layout.widget_edit_text_custom, this)
        mAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.CustomEditTextWidgetInput)
        initViews()
        setAttributesToViews()
        handleOverallClickLister()
        handleTextChange()
    }

    /**
     * init the res files this class needs
     */
    private fun initStyles() {
//        mColorSuccess = ContextCompat.getColor(context, R.color.auth_cyan)
        mColorSuccess = ContextCompat.getColor(context, R.color.white2)
        mColorFailure = ContextCompat.getColor(context, R.color.auth_red)
        mColorDefault = ContextCompat.getColor(context, R.color.auth_gray)
        mBackgroundFailure =
            ContextCompat.getDrawable(context, R.drawable.shape_state_error)
        mBackgroundSuccess =
            ContextCompat.getDrawable(context, R.drawable.shape_state_success)
        mSuccessStateIcon = ContextCompat.getDrawable(context, R.drawable.ic_check_mark)
        mFailureStateIcon = ContextCompat.getDrawable(context, R.drawable.ic_warning)
        mShowPasswordIcon = ContextCompat.getDrawable(context, R.drawable.ic_show)
        mHidePasswordIcon = ContextCompat.getDrawable(context, R.drawable.ic_hide)
    }

    /**
     * init all the views from the row.xml
     */
    private fun initViews() {
        mImageViewType = findViewById(R.id.auth_input_img_field_type)
        mViewVisibilityClick = findViewById(R.id.auth_input_visibility_click)
        mImageViewPasswordVisibility = findViewById(R.id.auth_input_img_visibility)
        mImageViewValidation = findViewById(R.id.auth_input_img_validation)
        mImageViewClose = findViewById(R.id.auth_input_img_close)
        editTextInput = findViewById(R.id.auth_input_edt)
        mTextViewError = findViewById(R.id.auth_input_txt_error)
        mConstraintLayoutRoot = findViewById(R.id.auth_input_root)
    }

    /**
     * gets the attributes from xml and sets it to the views
     */
    private fun setAttributesToViews() {
        mImageViewType.setImageDrawable(mAttributes.getDrawable(R.styleable.CustomEditTextWidgetInput_imageType))
        mImageViewValidation.setImageDrawable(mAttributes.getDrawable(R.styleable.CustomEditTextWidgetInput_imageState))
        mBackgroundSuccess =
            mAttributes.getDrawable(R.styleable.CustomEditTextWidgetInput_background)
        mConstraintLayoutRoot.background = mBackgroundSuccess
        mColorDefaultIcon =
            mAttributes.getColor(
                R.styleable.CustomEditTextWidgetInput_defaultIconColor,
                mColorDefault!!
            )
        ImageViewCompat.setImageTintList(
            mImageViewType,
            ColorStateList.valueOf(mColorDefaultIcon!!)
        )
        editTextInput.hint = mAttributes.getString(R.styleable.CustomEditTextWidgetInput_hintText)
        setInputType(mAttributes.getInt(R.styleable.CustomEditTextWidgetInput_inputType, 0))
        mAttributes.recycle()
    }

    /**
     * user only can click on mEditTextInput
     * to set focus on the edittext when want
     * to type
     *
     * this function adds different views onclick
     * so that where user clicks on the customView
     * it put focus on the edittext
     */
    private fun handleOverallClickLister() {
        mImageViewType.setOnClickListener {
            editTextInput.requestFocus()
        }
    }

    /**
     * handles all the text change effects
     * and updates the {textLiveData} when
     * user changes the edittext
     */
    private fun handleTextChange() {
        var hasFocus = false
        mTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val txt = p0.toString()
                text = txt
                _textLiveData.value = text
                /*
                    (#1) -- if hasFocus and no text should be default style
                    (#2) -- if hasFocus and text is not empty user is typing
                    (#3) -- if not hasFocus and is text should set observable value
                        cause user filled the input and clicked on someWhere else
                        and input is ready for validating
                 */
                if (txt == "") {
                    // should be set to default style (#1)
                    setToDefault()
                } else if (hasFocus && txt.isNotEmpty()) {
                    // should be typing (#2)
                    /*
                        if input is filled and user change the password input
                        visibility there is a bug where input state will change to
                        is typing.

                        with this block it jumps over the setToTyping
                        when password visibility changed and fixes the bug
                     */
                    if (mInputState == InputState.IS_PASSWORD_VISIBILITY)
                        mInputState = InputState.IS_DEFAULT
                    else
                        setToTyping()
                } else if (!hasFocus && txt.isNotEmpty()) {
                    // if setTextInput get called this if happens
                    setToTyping()
                    _textFocusLiveData.value = txt
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
        editTextInput.addTextChangedListener(mTextWatcher)
        editTextInput.setOnFocusChangeListener { view, b ->
            val text = (view as EditText).text.toString()
            hasFocus = b
            if (!hasFocus && text.isNotEmpty()) {
                // user should be validated (#3)
                _textFocusLiveData.value = text
            }
            /*
                 if input is filled and user change the focus by clicking on other views
                 and then change the password input visibility there is a bug where input
                 state will change to is typing.

                 with this block it jumps over the setToTyping
                 when password visibility changed and fixes the bug
             */
            if (hasFocus && text.isNotEmpty() && mInputState == InputState.IS_PASSWORD_VISIBILITY)
                mInputState = InputState.IS_DEFAULT
        }

    }

    /**
     * this function handles init of the input type of the edit text
     * like password or email
     */
    private fun setInputType(inputType: Int) {
        mInputType = inputType
        when (inputType) {
            NONE -> {
                editTextInput.inputType = InputType.TYPE_CLASS_TEXT
            }
            EMAIL -> {
                editTextInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            PASSWORD -> {
                editTextInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                editTextInput.setSelection(editTextInput.text!!.length)
                setInputTypeToPassword()
            }
            TEXT -> {
                editTextInput.inputType = InputType.TYPE_CLASS_TEXT
            }
            SEARCH -> {
                mImageViewClose.apply {
                    visibility = VISIBLE
                    setOnClickListener {
                        editTextInput.text.clear()
                    }
                }
            }
            EMPTY -> {
                mImageViewType.visibility = GONE
                editTextInput.filters = arrayOf<InputFilter>(LengthFilter(60))
            }
            TEXT_VIEW -> {
                editTextInput.inputType = InputType.TYPE_CLASS_TEXT
                setInputTypeToTextView()
            }
            NUMBER -> {
                editTextInput.inputType = numberInputType
                mImageViewType.visibility = GONE
            }
        }
    }

    fun clearText() {
        editTextInput.text.clear()
        setToDefault()
    }

    fun hidePasswordVisibility(isVisible: Boolean = false) {
        mShouldPasswordShow = isVisible
    }

    private fun setInputTypeToTextView() {
        editTextInput.setEditTextAsTextView()
        editTextInput.setTextColor(mColorDefault!!)
        editTextInput.gravity = Gravity.CENTER_VERTICAL
    }

    /**
     * handles password visibility change effects
     */
    private fun setInputTypeToPassword() {
        mIsPasswordVisibilityIconVisible = true
        handlePasswordVisibilityClickEvent()
    }

    /**
     * handles if the visibility icon should be hidden or not
     */
    private fun handlePasswordVisibility(isVisible: Boolean) {
        if (mShouldPasswordShow) {
            if (isVisible) {
                mImageViewPasswordVisibility.visibility = VISIBLE
                mViewVisibilityClick.isClickable = true
            } else {
                mImageViewPasswordVisibility.visibility = GONE
                mViewVisibilityClick.isClickable = false
            }
        }
    }

    private fun handlePasswordVisibilityClickEvent() {
        mViewVisibilityClick.setOnClickListener {
            mInputState = InputState.IS_PASSWORD_VISIBILITY
            if (mIsPasswordVisible) {
                /* if isPasswordVisible true should show hide
                so next time user could change it to visible */
                mImageViewPasswordVisibility.setImageDrawable(mHidePasswordIcon)
                // changing the input visibility in edittext to hide
                editTextInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                //placing cursor at the end of the text
                editTextInput.setSelection(editTextInput.text.toString().length)
                // update the stage of Visibility
                mIsPasswordVisible = false
            } else {
                /* if isPasswordVisible false should show visible
                so next time user could change it to hide */
                mImageViewPasswordVisibility.setImageDrawable(mShowPasswordIcon)
                // changing the input visibility in edittext to visible
                editTextInput.transformationMethod = PasswordTransformationMethod.getInstance()
                //placing cursor at the end of the text
                editTextInput.setSelection(editTextInput.text.toString().length)
                // update the stage of Visibility
                mIsPasswordVisible = true
            }
        }
    }

    /**
     * get called on fragment/activity when
     * want to show input is validated with regex
     */
    fun inputIsValid(showValidIcon: Boolean, shouldChangeColor: Boolean = false) {
        mInputState = InputState.IS_VALIDATED
        showValidationIcon(showValidIcon)
        if (shouldChangeColor) {
            setColorToViews(mColorSuccess!!)
            handleError(null)
        }
    }

    /**
     * get called on fragment/activity when
     * want to show input is invalid with regex
     * and should show error to user
     */
    fun inputIsInvalid(errorMsg: String?) {
        mInputState = InputState.IS_NOT_VALIDATED
        setToError(errorMsg)
    }

    private fun setToTyping() {
        /*
            if this if don't exist this function will change all colors every
            user type new character.
            this function makes sure that it only change the color when the state has
            changed from typing to error or reverse.
         */
        if (mInputState != InputState.IS_TYPING && mInputState != InputState.IS_VALIDATED) {
            // show passwordVisibilityIcon to visible when typing
            if (mInputType == PASSWORD)
                handlePasswordVisibility(true)

            if (mInputType != TEXT_VIEW)
                setColorToViews(mColorSuccess!!)
            handleError(null)
        } else if (mInputState == InputState.IS_VALIDATED) {
            showValidationIcon(false)
        }
        mInputState = InputState.IS_TYPING
    }


    fun setTextInput(txt: String?) {
        editTextInput.setText(txt)
        _textFocusLiveData.value = txt ?: ""
    }

    fun setUsernameInput(txt: String?) {
        //if 'txt' is an email, it means that user has not set a username in signup process (and b4a uses his/her email instead), so it should not be shown
        if (!AuthUtil.isEmailValid(txt ?: "")) {
            editTextInput.setText(txt)
            _textFocusLiveData.value = txt ?: ""
        }
    }

    private fun setToError(msg: String?) {
        mInputState = InputState.IS_ERROR
        setColorToViews(mColorFailure!!)
        if (msg != null) {
            showValidationIcon(true)
            handleError(msg)
        }
    }

    private fun setToDefault() {
        // show passwordVisibilityIcon to inVisible when default
        if (mInputType == PASSWORD)
            handlePasswordVisibility(false)

        mInputState = InputState.IS_DEFAULT
        setColorToViews(mColorDefaultIcon!!)
        showValidationIcon(false)
        handleError(null)
    }

    private fun changeValidationIcon(isError: Boolean) {
        if (!isError) {
            mImageViewValidation.setImageDrawable(mFailureStateIcon)
            mConstraintLayoutRoot.background = mBackgroundFailure
        } else {
            // onTyping only works without validation icon this resets the sate
            showValidationIcon(false)
            mImageViewValidation.setImageDrawable(mSuccessStateIcon)
            mConstraintLayoutRoot.background = mBackgroundSuccess
        }
    }

    /**
     * change the colors of the views when
     * onTyping or onError etc...
     */
    private fun setColorToViews(color: Int) {
        ImageViewCompat.setImageTintList(mImageViewType, ColorStateList.valueOf(color))
        ImageViewCompat.setImageTintList(mImageViewValidation, ColorStateList.valueOf(color))
        editTextInput.setTextColor(color)
        when (color) {
            mColorFailure -> {
                changeValidationIcon(false)
            }
            mColorSuccess -> {
                // for changing the close button to white and default
                ImageViewCompat.setImageTintList(
                    mImageViewClose,
                    ColorStateList.valueOf(mColorDefaultIcon!!)
                )
                changeValidationIcon(true)
            }
            else -> {
                ImageViewCompat.setImageTintList(
                    mImageViewClose,
                    ColorStateList.valueOf(mColorDefault!!)
                )
                changeValidationIcon(true)
            }
        }
    }

    /**
     * handles mTextViewError visibility and showing
     * appropriate message to the ui
     *
     * if null means success else is error
     */
    private fun handleError(msg: String?) {
        if (msg != null) {
            mTextViewError.visibility = View.VISIBLE
            mTextViewError.text = msg
        } else {
            mTextViewError.visibility = View.GONE
        }
    }

    /**
     * shows and hides the Valid Answer Icon
     *
     * should not be called when using password visibility
     * function
     */
    private fun showValidationIcon(showValidIcon: Boolean) {
        mShowValidation = showValidIcon
        // don't show the validation icon
        // if password visibility icon is visible
        if (mShowValidation && !mIsPasswordVisibilityIconVisible) {
            mImageViewValidation.visibility = View.VISIBLE
        } else {
            mImageViewValidation.visibility = View.INVISIBLE
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        editTextInput.removeTextChangedListener(mTextWatcher)
        editTextInput.onFocusChangeListener = null
    }

    fun requestFocusToEditText() {
        editTextInput.requestFocus()
        editTextInput.setSelection(editTextInput.length())
    }

    fun changeToTextView() {
        editTextInput.isEnabled = false
    }
}

enum class InputState {
    IS_DEFAULT,
    IS_ERROR,
    IS_TYPING,
    IS_NOT_VALIDATED,
    IS_VALIDATED,
    IS_PASSWORD_VISIBILITY
}

interface CustomEditTextWidgetInputType {
    companion object {
        const val NONE: Int = 0
        const val EMAIL: Int = 1
        const val PASSWORD: Int = 2
        const val TEXT: Int = 3
        const val SEARCH: Int = 4
        const val EMPTY: Int = 5
        const val TEXT_VIEW: Int = 6
        const val NUMBER: Int = 7
    }
}
