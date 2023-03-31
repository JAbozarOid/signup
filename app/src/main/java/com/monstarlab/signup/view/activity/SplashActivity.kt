package com.monstarlab.signup.view.activity

import androidx.activity.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.ActivitySplashBinding
import com.monstarlab.signup.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }

}