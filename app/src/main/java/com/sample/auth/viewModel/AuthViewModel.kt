package com.sample.auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.sample.auth.constants.AppConstants.EMAIL_IS_EMPTY
import com.sample.auth.constants.AppConstants.EMAIL_IS_INCORRECT
import com.sample.auth.constants.AppConstants.PASSWORD_IS_EMPTY
import com.sample.auth.constants.AppConstants.PASSWORD_REGEX
import com.sample.auth.constants.AppConstants.USERNAME_IS_INCORRECT
import com.sample.auth.util.AuthUtil
import com.sample.data.entity.signin.SignInDataModel
import com.sample.data.entity.signin.SignInResultModel
import com.sample.data.entity.signup.SignupDataModel
import com.sample.data.entity.signup.SignupResultModel
import com.sample.data.util.ApiResponse
import com.sample.data.util.NetworkUtil.safeApiCall
import com.sample.domain.usecase.SignInUseCase
import com.sample.domain.usecase.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    var emailErrorSignup: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    var passwordErrorSignup: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    var usernameErrorSignin: LiveEvent<String> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    var signupResData: LiveEvent<ApiResponse<String>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)

    private var _signInResData: LiveEvent<ApiResponse<List<SignInResultModel>>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val signInResData: LiveData<ApiResponse<List<SignInResultModel>>> get() = _signInResData


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

    fun isUsernameValid(username: String): Boolean {
        return if (AuthUtil.isUsernameNotEmpty(username))
            true
        else {
            usernameErrorSignin.value = USERNAME_IS_INCORRECT
            false
        }
    }

    fun isEmailAndPasswordValid(
        email: String,
        password: String,
    ): Boolean {
        return if (email.trim().isNotEmpty() || password.trim().isNotEmpty())
            isEmailValid(
                email,
                "signup"
            ) && isPasswordValid(
                password,
                "signup"
            )
        else
            isEmailValid(email, "signup") &&
                    isPasswordValid(password, "signup")
    }

    fun isEmailValid(email: String, type: String): Boolean {
        return when {
            email.trim().isEmpty() -> {
                when (type) {
                    "signup" -> {
                        emailErrorSignup.value = EMAIL_IS_INCORRECT
                    }
                }
                false
            }

            !AuthUtil.isEmailValid(email.trim()) -> {
                when (type) {
                    "signup" -> {
                        emailErrorSignup.value = EMAIL_IS_INCORRECT
                    }
                }
                false
            }

            else -> {
                true
            }
        }
    }

    fun isPasswordValid(password: String, type: String): Boolean {
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

    fun requestSignInData(signInDataModel: SignInDataModel) {
        viewModelScope.launch {
            _signInResData.value = safeApiCall {
                signInUseCase.execute(signInDataModel) as Response<List<SignInResultModel>>
            }
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