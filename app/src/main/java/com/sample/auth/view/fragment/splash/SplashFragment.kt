package com.sample.auth.view.fragment.splash

import android.view.View
import androidx.fragment.app.activityViewModels
import com.sample.auth.R
import com.sample.auth.command.NavigationCommand
import com.sample.auth.databinding.FragmentSplashBinding
import com.sample.auth.view.activity.SignupActivity
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by activityViewModels()


    override fun initLayout(view: View) {
        super.initLayout(view)
        viewModel.fire{
            delay(1000)
            //viewModel.navigationCommand.postValue(NavigationCommand.ToActivity(MainActivity::class.java, finishSourceActivity = true))
            viewModel.navigationCommand.postValue(NavigationCommand.ToActivity(SignupActivity::class.java, finishSourceActivity = true))
        }
    }


}