package com.monstarlab.signup.view.dialog.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.monstarlab.data.BR
import com.monstarlab.signup.viewModel.BaseViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val viewId: Int
) : BottomSheetDialogFragment() {

    protected lateinit var viewBinding: VB
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureViewBinding(inflater, container)
        configureHeight()
        return viewBinding.root
    }

    fun configureHeight() {
        
    }

    private fun configureViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        viewBinding = DataBindingUtil.inflate(inflater, viewId, container, false)
        viewBinding.setLifecycleOwner { lifecycle }
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    open fun initLayout() {}

}