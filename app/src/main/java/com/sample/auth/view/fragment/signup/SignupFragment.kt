package com.sample.auth.view.fragment.signup

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sample.auth.R
import com.sample.auth.databinding.FragmentSignupBinding
import com.sample.data.entity.signup.SignupDataModel
import com.sample.data.util.ApiResponse
import com.sample.auth.view.activity.MainActivity
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignupFragment :
    BaseFragment<FragmentSignupBinding, SignupViewModel>(R.layout.fragment_signup),
    View.OnClickListener {

    override val viewModel: SignupViewModel by viewModels()

    override fun initLayout(view: View) {
        super.initLayout(view)
        viewBinding.btnCreateAccount.setOnClickListener(this)

        validateEmailInput()
        validatePasswordInput()
        observeInputErrors()

        observeRemoteSignupResData()
        observeLocalSingUpResData()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.custom_button_btn -> {
                val isFilled = viewModel.isEmailAndPasswordFilled(
                    email = viewBinding.edtEmail.text,
                    password = viewBinding.edtPassword.text,
                )

                if (isFilled) {
                    val isValid = viewModel.isEmailAndPasswordValid(
                        email = viewBinding.edtEmail.text,
                        password = viewBinding.edtPassword.text,
                    )
                    if (isValid) {
                        requestSignupData(
                            email = viewBinding.edtEmail.text,
                            password = viewBinding.edtPassword.text,
                        )
                    }
                }
            }
        }
    }

    private fun requestSignupData(email: String, password: String) {
        // call api
        lifecycleScope.launch {
            showMainLoadingState()
            delay(1000)
            viewModel.requestSignupData(SignupDataModel().apply {
                this.email = email
                this.password = password
            })
        }
    }

    private fun observeRemoteSignupResData() {
        viewModel.signupResData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    hideMainLoadingState()
                    showMessage(it.errorMessage)
                    viewModel.parseLocalResJson()
                }
                is ApiResponse.ErrorTryAgain -> {
                    hideMainLoadingState()
                    showMessage(it.errorMessage)
                    viewModel.parseLocalResJson()
                }
                is ApiResponse.Loading -> {
                    showMainLoadingState()
                }
                is ApiResponse.Success -> {
                    hideMainLoadingState()
                    showMessage(viewModel.parseRemoteResJson(it.data))
                    navigationCommand()
                }
            }
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    private fun navigationCommand() {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun observeLocalSingUpResData() {
        viewModel.signupResLocalData.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            navigationCommand()
        }
    }

    private fun showMainLoadingState() {
        if (viewBinding.loading.visibility != View.VISIBLE) {
            viewBinding.loading.visibility = View.VISIBLE
            viewBinding.btnCreateAccount.visibility = View.INVISIBLE
        }
    }

    private fun hideMainLoadingState() {
        viewBinding.loading.visibility = View.INVISIBLE
        viewBinding.btnCreateAccount.visibility = View.VISIBLE
    }

    private fun validateEmailInput() {
        viewBinding.edtEmail.textFocusLiveData.observe(viewLifecycleOwner) {
            if (viewModel.validateEmail(it, "signup")) {
                viewBinding.edtEmail.inputIsValid(true)
            }
        }
    }

    private fun validatePasswordInput() {
        viewBinding.edtPassword.textFocusLiveData.observe(viewLifecycleOwner) {
            if (viewModel.validatePassword(it, "signup")) {
                viewBinding.edtPassword.inputIsValid(true)
            }
        }
    }

    private fun observeInputErrors() {
        viewModel.emailErrorSignup.observe(
            viewLifecycleOwner
        ) { error ->
            viewBinding.edtEmail.inputIsInvalid(error)
        }

        viewModel.passwordErrorSignup.observe(
            viewLifecycleOwner
        ) { error ->
            viewBinding.edtPassword.inputIsInvalid(error)
        }
    }
}