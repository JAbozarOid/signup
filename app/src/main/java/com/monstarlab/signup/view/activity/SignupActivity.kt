package com.monstarlab.signup.view.activity

import androidx.activity.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.ActivitySignupBinding
import com.monstarlab.signup.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity :
    BaseActivity<ActivitySignupBinding, SignupViewModel>(R.layout.activity_signup) {
    override val viewModel: SignupViewModel by viewModels()
}