package com.monstarlab.signup.view.activity

import androidx.activity.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.ActivityVisionBinding
import com.monstarlab.signup.viewModel.VisionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisionActivity :
    BaseActivity<ActivityVisionBinding, VisionViewModel>(R.layout.activity_vision) {
    override val viewModel: VisionViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }
}