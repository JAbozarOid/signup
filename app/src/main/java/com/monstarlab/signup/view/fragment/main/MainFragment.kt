package com.monstarlab.signup.view.fragment.main

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.FragmentMainBinding
import com.monstarlab.signup.view.dialog.bottomsheet.SettingBottomSheet
import com.monstarlab.signup.view.fragment.BaseFragment
import com.monstarlab.signup.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val viewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var settingBottomSheet: SettingBottomSheet

    override fun initLayout(view: View) {
        super.initLayout(view)
        viewBinding.txtTestB.setOnClickListener {
           viewModel.showSetting()
        }
        println("SWEET ${this.javaClass.simpleName} created")

        viewModel.settingItems.observe(viewLifecycleOwner) {
            settingBottomSheet.show(childFragmentManager, "test")
        }
    }
}