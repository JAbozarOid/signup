package com.sample.auth.view.dialog.bottomsheet

import androidx.fragment.app.activityViewModels
import com.sample.auth.R
import com.sample.auth.databinding.BottomSheetSettingBinding
import com.sample.auth.viewModel.MainViewModel
import javax.inject.Inject

class SettingBottomSheet @Inject constructor() :
    BaseBottomSheet<BottomSheetSettingBinding, MainViewModel>(R.layout.bottom_sheet_setting) {

    override val viewModel: MainViewModel by activityViewModels()

    override fun initLayout() {
        super.initLayout()
    }

}