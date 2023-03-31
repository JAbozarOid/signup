package com.monstarlab.signup.view.activity

import androidx.activity.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.ActivityOrderBinding
import com.monstarlab.signup.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : BaseActivity<ActivityOrderBinding,OrderViewModel> (R.layout.activity_order) {
    override val viewModel: OrderViewModel by viewModels()
}