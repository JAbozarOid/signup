package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.ActivitySplashBinding
import com.sample.auth.databinding.ActivityTodoBinding
import com.sample.auth.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity :
    BaseActivity<ActivityTodoBinding, SplashViewModel>(R.layout.activity_todo) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }

}