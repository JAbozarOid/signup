package com.sample.auth.view.fragment.order

import android.view.View
import androidx.fragment.app.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.FragmentOrderHomeBinding
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderHomeFragment :
    BaseFragment<FragmentOrderHomeBinding, OrderViewModel>(R.layout.fragment_order_home) {
    override val viewModel: OrderViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
        println("SWEET ${this.javaClass.simpleName} created")
    }
}