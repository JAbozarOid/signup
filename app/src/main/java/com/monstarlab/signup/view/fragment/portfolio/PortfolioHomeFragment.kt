package com.monstarlab.signup.view.fragment.portfolio

import android.view.View
import androidx.fragment.app.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentPortfolioHomeBinding
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.PortfolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioHomeFragment :
    BaseFragment<FragmentPortfolioHomeBinding, PortfolioViewModel>(R.layout.fragment_portfolio_home) {

    override val viewModel: PortfolioViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
        println("SWEET ${this.javaClass.simpleName} created")
    }

}