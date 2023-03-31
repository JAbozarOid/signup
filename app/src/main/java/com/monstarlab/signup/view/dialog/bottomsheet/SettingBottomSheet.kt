package com.monstarlab.signup.view.dialog.bottomsheet

import androidx.fragment.app.activityViewModels
import com.monstarlab.signup.R
import com.monstarlab.signup.databinding.BottomSheetSettingBinding
import com.monstarlab.signup.viewModel.MainViewModel
import javax.inject.Inject

class SettingBottomSheet @Inject constructor() :
    BaseBottomSheet<BottomSheetSettingBinding, MainViewModel>(R.layout.bottom_sheet_setting) {

    override val viewModel: MainViewModel by activityViewModels()

    override fun initLayout() {
        super.initLayout()
    }

}