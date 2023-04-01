package com.monstarlab.signup.viewModel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.monstarlab.data.entity.signup.SignupDataModel
import com.monstarlab.data.entity.signup.SignupResultModel
import com.monstarlab.data.util.ApiResponse
import com.monstarlab.data.util.NetworkUtil.safeApiCall
import com.monstarlab.domain.usecase.SignupUseCase
import com.monstarlab.signup.constants.AppConstants.EMAIL_IS_EMPTY
import com.monstarlab.signup.constants.AppConstants.EMAIL_IS_IN_CORRECT
import com.monstarlab.signup.constants.AppConstants.PASSWORD_IS_EMPTY
import com.monstarlab.signup.constants.AppConstants.PASSWORD_REGEX
import com.monstarlab.signup.util.AuthUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(var signupUseCase: SignupUseCase) : BaseViewModel() {

    var emailErrorSignup: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    var passwordErrorSignup: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    var signupResData: LiveEvent<ApiResponse<String>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    var signupResLocalData: LiveEvent<String> =
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

    suspend fun requestSignupData(singnupDataModel: SignupDataModel) {
        signupResData.value =
            safeApiCall {
                signupUseCase.execute(singnupDataModel)
            }
    }

    fun parseRemoteResJson(json: String): String {
        val response = Gson().fromJson<SignupResultModel>(
            json, object : TypeToken<SignupResultModel>() {}.type
        )
        return response.token
    }

    /**
     * if mockable was stopped accidentally
     */
    fun parseLocalResJson() {
        val json = "{\n" +
                "\"token\": \"SomeLongString\"\n" +
                "}"
        val response = Gson().fromJson<SignupResultModel>(
            json, object : TypeToken<SignupResultModel>() {}.type
        )
        signupResLocalData.value = response.token
    }
}