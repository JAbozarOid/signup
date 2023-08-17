package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.FragmentPortfolioHomeBinding
import com.sample.auth.viewModel.PortfolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioActivity :
    BaseActivity<FragmentPortfolioHomeBinding, PortfolioViewModel>(R.layout.fragment_portfolio_home) {

    override val viewModel: PortfolioViewModel by viewModels()

}