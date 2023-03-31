package com.monstarlab.signup.view.fragment.signup

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.monstarlab.data.util.ApiResponse
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentSignupBinding
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_loading.view.*
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

        observeSignupResData()
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
            viewModel.requestSignupData(email = email, password = password)
        }
    }

    private fun observeSignupResData() {
        viewModel.signupResData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    hideMainLoadingState()
                }
                is ApiResponse.ErrorTryAgain -> {

                }
                is ApiResponse.Loading -> {
                    showMainLoadingState()
                }
                is ApiResponse.Success -> TODO()
            }
        }
    }

    private fun showMainLoadingState() {
        if (viewBinding.layoutLoading.cns_loading?.visibility != View.VISIBLE) {
            viewBinding.layoutLoading.cns_loading?.visibility = View.VISIBLE
            viewBinding.btnCreateAccount.visibility = View.INVISIBLE
        }
    }

    private fun hideMainLoadingState() {
        viewBinding.layoutLoading.cns_loading?.visibility = View.INVISIBLE
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