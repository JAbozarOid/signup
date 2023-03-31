package com.monstarlab.signup.view.activity

import androidx.activity.viewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.ActivitySearchBinding
import com.monstarlab.signup.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity :
    BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {
    override val viewModel: SearchViewModel by viewModels()
}