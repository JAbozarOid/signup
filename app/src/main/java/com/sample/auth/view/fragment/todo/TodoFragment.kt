package com.sample.auth.view.fragment.todo

import android.view.View
import androidx.fragment.app.activityViewModels
import com.sample.auth.R
import com.sample.auth.command.NavigationCommand
import com.sample.auth.databinding.FragmentSplashBinding
import com.sample.auth.databinding.FragmentTodoBinding
import com.sample.auth.view.activity.SignupActivity
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class TodoFragment :
    BaseFragment<FragmentTodoBinding, SplashViewModel>(R.layout.fragment_todo) {

    override val viewModel: SplashViewModel by activityViewModels()


    override fun initLayout(view: View) {
        super.initLayout(view)
    }


}