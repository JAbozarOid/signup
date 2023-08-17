package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.ActivitySignupBinding
import com.sample.auth.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity :
    BaseActivity<ActivitySignupBinding, SignupViewModel>(R.layout.activity_signup) {
    override val viewModel: SignupViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }
}