package com.sample.auth.view.fragment.signin

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sample.auth.R
import com.sample.auth.databinding.FragmentSigninBinding
import com.sample.auth.view.activity.MainActivity
import com.sample.auth.view.activity.TodoActivity
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.AuthViewModel
import com.sample.data.entity.signin.SignInDataModel
import com.sample.data.util.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SigninFragment : BaseFragment<FragmentSigninBinding, AuthViewModel>(R.layout.fragment_signin),
    View.OnClickListener {

    override val viewModel: AuthViewModel by viewModels()

    override fun initLayout(view: View) {
        super.initLayout(view)

        viewBinding.btnSignIn.setOnClickListener(this)
        validateUsernameInput()
        observeUsernameInputErrors()
        observeRemoteSignInResData()
    }

    private fun validateUsernameInput() {
        viewBinding.edtUsername.textFocusLiveData.observe(viewLifecycleOwner) {
            if (viewModel.isUsernameValid(it)) {
                viewBinding.edtUsername.inputIsValid(true)
            }
        }
    }

    private fun observeUsernameInputErrors() {
        viewModel.usernameErrorSignin.observe(viewLifecycleOwner) { error ->
            viewBinding.edtUsername.inputIsInvalid(errorMsg = error)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.custom_button_btn -> {
                if (viewModel.isUsernameValid(viewBinding.edtUsername.text)) {
                    showMainLoadingState()
                    viewModel.requestSignInData(SignInDataModel().apply {
                        this.username = viewBinding.edtUsername.text
                    })
                }
            }
        }
    }

    private fun observeRemoteSignInResData() {
        viewModel.signInResData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    hideMainLoadingState()
                    Log.d("ABOZAR", "error: ${it.errorMessage}")
                    showMessage(it.errorMessage)
                }

                is ApiResponse.ErrorTryAgain -> {
                    hideMainLoadingState()
                    Log.d("ABOZAR", "error try: ${it.errorMessage}")
                    showMessage(it.errorMessage)
                }

                is ApiResponse.Loading -> {
                    showMainLoadingState()
                }

                is ApiResponse.Success -> {
                    hideMainLoadingState()
                    Log.d("ABOZAR", "success: ${it.data}")
                    if (it.data.isEmpty()) {
                        showMessage("User Not found")
                    } else {
                        showMessage(it.data[0].username)
                        navigationCommand(id = it.data[0].id)
                    }
                }
            }
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    private fun navigationCommand(id : Int) {
        val intent = Intent(activity, TodoActivity::class.java)
        intent.putExtra("id",id)
        activity?.startActivity(intent)
        activity?.finish()
    }

    private fun showMainLoadingState() {
        if (viewBinding.loading.visibility != View.VISIBLE) {
            viewBinding.loading.visibility = View.VISIBLE
            viewBinding.btnSignIn.visibility = View.INVISIBLE
        }
    }

    private fun hideMainLoadingState() {
        viewBinding.loading.visibility = View.INVISIBLE
        viewBinding.btnSignIn.visibility = View.VISIBLE
    }
}