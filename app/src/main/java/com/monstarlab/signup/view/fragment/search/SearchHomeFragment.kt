package com.monstarlab.signup.view.fragment.search

import android.view.View
import androidx.fragment.app.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentSplashBinding
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHomeFragment : BaseFragment<FragmentSplashBinding,SearchViewModel>(R.layout.fragment_search_home){
    override val viewModel: SearchViewModel by viewModels()
    override fun initLayout(view: View) {
        super.initLayout(view)
        println("SWEET ${this.javaClass.simpleName} created")
    }
}