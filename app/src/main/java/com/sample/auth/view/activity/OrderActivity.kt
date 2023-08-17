package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.ActivityOrderBinding
import com.sample.auth.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : BaseActivity<ActivityOrderBinding,OrderViewModel> (R.layout.activity_order) {
    override val viewModel: OrderViewModel by viewModels()
}