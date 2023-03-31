package com.monstarlab.signup.viewModel

import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.monstarlab.data.repository.SingupRepository
import com.monstarlab.data.util.ApiResponse
import com.monstarlab.domain.usecase.SignupUseCase
import com.monstarlab.signup.constants.TextConstant.EMAIL_IS_EMPTY
import com.monstarlab.signup.constants.TextConstant.EMAIL_IS_IN_CORRECT
import com.monstarlab.signup.constants.TextConstant.PASSWORD_IS_EMPTY
import com.monstarlab.signup.constants.TextConstant.PASSWORD_REGEX
import com.monstarlab.signup.util.AuthUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : BaseViewModel() {

    //private lateinit var mNetworkListener: NetworkListener


    var emailErrorSignup: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    var passwordErrorSignup: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    var signupResData: LiveEvent<ApiResponse<String>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    fun isEmailAndPasswordFilled(
        email: String,
        password: String,
    ): Boolean {
        return when {
            !AuthUtil.isEmailAndPasswordFilled(
                email,
                password,
            ) -> {
                if (email.isEmpty()) {
                    emailErrorSignup.value = EMAIL_IS_EMPTY
                }
                if (password.isEmpty()) {
                    passwordErrorSignup.value = PASSWORD_IS_EMPTY
                }
                false
            }
            else -> {
                true
            }
        }

    }

    fun isEmailAndPasswordValid(
        email: String,
        password: String,
    ): Boolean {
        return if (email.trim().isNotEmpty() || password.trim().isNotEmpty())
            validateEmail(
                email,
                "signup"
            ) && validatePassword(
                password,
                "signup"
            )
        else
            validateEmail(email, "signup") &&
                    validatePassword(password, "signup")
    }

    fun validateEmail(email: String, type: String): Boolean {
        return when {
            email.trim().isEmpty() -> {
                when (type) {
                    "signup" -> {
                        emailErrorSignup.value = EMAIL_IS_IN_CORRECT
                    }
                }
                false
            }
            !AuthUtil.isEmailValid(email.trim()) -> {
                when (type) {
                    "signup" -> {
                        emailErrorSignup.value = EMAIL_IS_IN_CORRECT
                    }
                }
                false
            }
            else -> {
                true
            }
        }
    }

    fun validatePassword(password: String, type: String): Boolean {
        return when {
            password.isEmpty() -> {
                if (type == "signup") {
                    passwordErrorSignup.value = PASSWORD_IS_EMPTY
                }
                false
            }
            !AuthUtil.isPasswordValid(password) -> {
                if (type == "signup") {
                    passwordErrorSignup.value = PASSWORD_REGEX
                }
                false
            }
            else -> {
                true
            }
        }

    }

    fun requestSignupData(email: String, password: String) {
       /* genericRequestCollect(
            body = { signupUseCase.execute(Unit) as ApiResponse<String> },
            viewModelScope
        ) {
            signupResData.postValue(it)
        }*/
    }
}