package com.monstarlab.signup.view.fragment.splash

import android.view.View
import androidx.fragment.app.activityViewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.command.NavigationCommand
import com.monstarlab.signup.databinding.FragmentSplashBinding
import com.monstarlab.signup.view.activity.MainActivity
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by activityViewModels()


    override fun initLayout(view: View) {
        super.initLayout(view)
        viewModel.fire{
            delay(2000)
            viewModel.navigationCommand.postValue(NavigationCommand.ToActivity(MainActivity::class.java, finishSourceActivity = true))
        }
    }


}