package com.monstarlab.signup.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.monstarlab.signup.R

class LoadingDialog(): DialogFragment() {
    companion object {
        fun newInstance(): LoadingDialog {
            val args = Bundle()
            val fragment = LoadingDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.loading_dialog_style)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.loading, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(context?.getDrawable(R.color.loadingBackground))
        return viewBinding.root
    }

}