package com.monstarlab.signup.view.activity

import androidx.activity.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentPortfolioHomeBinding
import com.monstarlab.signup.viewModel.PortfolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PortfolioActivity :
    BaseActivity<FragmentPortfolioHomeBinding, PortfolioViewModel>(R.layout.fragment_portfolio_home) {

    override val viewModel: PortfolioViewModel by viewModels()

}