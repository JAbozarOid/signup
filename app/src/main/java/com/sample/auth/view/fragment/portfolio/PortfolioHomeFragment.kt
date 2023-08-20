package com.sample.auth.view.fragment.portfolio

import android.view.View
import androidx.fragment.app.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.FragmentPortfolioHomeBinding
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.PortfolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioHomeFragment :
    BaseFragment<FragmentPortfolioHomeBinding, PortfolioViewModel>(R.layout.fragment_portfolio_home) {

    override val viewModel: PortfolioViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
    }

}