package com.sample.auth.view.fragment.vision

import android.view.View
import androidx.fragment.app.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.FragmentVisionHomeBinding
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.VisionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisionHomeFragment :
    BaseFragment<FragmentVisionHomeBinding, VisionViewModel>(R.layout.fragment_vision_home) {
    override val viewModel: VisionViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
    }
}