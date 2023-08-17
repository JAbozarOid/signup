package com.sample.auth.view.fragment.signin

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sample.auth.R
import com.sample.auth.command.NavigationCommand
import com.sample.auth.databinding.FragmentSigninBinding
import com.sample.auth.databinding.FragmentSignupBinding
import com.sample.data.entity.signup.SignupDataModel
import com.sample.data.util.ApiResponse
import com.sample.auth.view.activity.MainActivity
import com.sample.auth.view.activity.SigninActivity
import com.sample.auth.view.activity.SignupActivity
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SigninFragment :
    BaseFragment<FragmentSigninBinding, SignupViewModel>(R.layout.fragment_signin),
    View.OnClickListener {

    override val viewModel: SignupViewModel by viewModels()

    override fun initLayout(view: View) {
        super.initLayout(view)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    private fun navigationCommand() {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun showMainLoadingState() {
        if (viewBinding.loading.visibility != View.VISIBLE) {
            viewBinding.loading.visibility = View.VISIBLE
            //viewBinding.btnCreateAccount.visibility = View.INVISIBLE
        }
    }

    private fun hideMainLoadingState() {
        viewBinding.loading.visibility = View.INVISIBLE
        //viewBinding.btnCreateAccount.visibility = View.VISIBLE
    }
}