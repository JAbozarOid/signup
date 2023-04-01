package com.monstarlab.signup.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import com.monstarlab.signup.R
import com.monstarlab.signup.util.reset
import com.monstarlab.signup.util.setSnackColor
import com.monstarlab.signup.util.setSnackConfig
import com.monstarlab.signup.view.dialog.LoadingDialog
import com.monstarlab.signup.view.widget.snack.SnackStatus
import com.monstarlab.signup.viewModel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val viewId: Int
) :
    AppCompatActivity() {

    protected lateinit var viewBinding: VB
    abstract val viewModel: VM
    private var snack: Snackbar? = null

    @Inject
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, viewId)
        initSnack()
        subscribeToViewModel()
        onActivityCreated()
        onCreated()

    }

    private fun initSnack() {
        try {
            val contextView = viewBinding.root.findViewById<View>(R.id.parent)
            snack = Snackbar.make(contextView, "", Snackbar.LENGTH_LONG)
        } catch (ignored: Exception) {
        }
    }

    protected open fun subscribeToViewModel() {

        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()

        viewModel.navigationCommand.observe(this) {
            if (it != null) {
                it.navigate(this@BaseActivity)
                viewModel.navigationCommand.postValue(null)
            }
        }
        viewModel.toast.observe(this) {
            if (it != null) {
                runOnUiThread {
                    delay(500)
                    Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
                    viewModel.toast.postValue(null)
                }
            }
        }
        /*viewModel.showLoading.observe(this) {
            if (it != null) {
                runOnUiThread {
                    delay(50)
                    if (it && !loadingDialog.isAdded) loadingDialog.show(
                        supportFragmentManager,
                        "loadingDialog"
                    ) else loadingDialog.dismissAllowingStateLoss()
                    viewModel.showLoading.postValue(null)
                }

            }
        }*/
        viewModel.showGreenSnack.observe(this) {
            if (it != null) {
                viewModel.showSnack.postValue(Pair(it, SnackStatus.GREEN))
                viewModel.showGreenSnack.postValue(null)
            }
        }
        viewModel.showDefaultSack.observe(this) {
            if (it != null) {
                viewModel.showSnack.postValue(Pair(it, SnackStatus.DEFAULT))
                viewModel.showDefaultSack.postValue(null)
            }
        }
        viewModel.showRedSnack.observe(this) {
            if (it != null) {
                viewModel.showSnack.postValue(Pair(it, SnackStatus.RED))
                viewModel.showRedSnack.postValue(null)
            }
        }
        viewModel.showSnack.observe(this) {
            if (it.first != null && it.second != null) {
                runOnUiThread {
                    snack?.let { snack ->

                        snack.reset()

                        if (snack.isShown)
                            snack.dismiss()
                        snack.setSnackConfig(it.first!!)
                        snack.setSnackColor(it.second!!)
                        delay(20)
                        snack.show()
                        return@runOnUiThread

                    }
                    viewModel.toast.postValue(it.first!!.message)
                }
                MainScope().launch {

                }
                viewModel.showSnack.postValue(Pair(null, null))
            }
        }
    }

    protected open fun onActivityCreated() {

    }

    protected open fun onCreated() {

    }

    override fun onStop() {
        super.onStop()
        viewModel.showLoading.postValue(false)
    }


    private fun runOnUiThread(method: suspend () -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            method()
        }
    }

}