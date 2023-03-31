package com.monstarlab.signup.view.fragment.order

import android.view.View
import androidx.fragment.app.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentOrderHomeBinding
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.OrderViewModel
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