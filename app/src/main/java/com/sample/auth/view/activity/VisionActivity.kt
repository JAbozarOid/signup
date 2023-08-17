package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.ActivityVisionBinding
import com.sample.auth.viewModel.VisionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisionActivity :
    BaseActivity<ActivityVisionBinding, VisionViewModel>(R.layout.activity_vision) {
    override val viewModel: VisionViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }
}