package com.monstarlab.signup.view.fragment.signup

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentSignupBinding
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration


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
        Toast.makeText(context,"call signup api",Toast.LENGTH_LONG).show()
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