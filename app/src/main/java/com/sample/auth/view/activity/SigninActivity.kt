package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.ActivitySigninBinding
import com.sample.auth.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigninActivity :
    BaseActivity<ActivitySigninBinding, SignupViewModel>(R.layout.activity_signin) {
    override val viewModel: SignupViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }
}