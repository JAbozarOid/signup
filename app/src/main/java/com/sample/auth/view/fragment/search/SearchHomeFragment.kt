package com.sample.auth.view.fragment.search

import android.view.View
import androidx.fragment.app.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.FragmentSplashBinding
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHomeFragment : BaseFragment<FragmentSplashBinding,SearchViewModel>(R.layout.fragment_search_home){
    override val viewModel: SearchViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
    }
}