package com.monstarlab.signup.view.fragment.vision

import android.view.View
import androidx.fragment.app.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentVisionHomeBinding
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.VisionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisionHomeFragment :
    BaseFragment<FragmentVisionHomeBinding, VisionViewModel>(R.layout.fragment_vision_home) {
    override val viewModel: VisionViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
        println("SWEET ${this.javaClass.simpleName} created")
    }
}