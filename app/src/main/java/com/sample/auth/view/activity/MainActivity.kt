package com.sample.auth.view.activity

import android.content.Context
import android.os.*
import androidx.activity.viewModels
import androidx.navigation.NavOptions
import com.sample.auth.R
import com.sample.auth.command.NavigationCommand
import com.sample.auth.databinding.ActivityMainBinding
import com.sample.auth.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

    private val vibrator: Vibrator? by lazy {
        getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }


    override fun onCreated() {
        super.onCreated()
        configureBottomNavigation()
        println("SWEET ${this.javaClass.simpleName} created")
    }

    private fun configureBottomNavigation() {

        viewBinding.bottomNavigation.selectedItemId = R.id.homeFragment

        viewBinding.bottomNavigation.setOnItemSelectedListener {
            vibrate()
            viewModel.navigationCommand.postValue(
                NavigationCommand.To(
                    it.itemId,
                    navOption = NavOptions.Builder().apply {
                        this.setEnterAnim(com.google.android.material.R.anim.abc_fade_in)
                        this.setExitAnim(com.google.android.material.R.anim.abc_fade_out)
                    })
            )
            true
        }
        viewBinding.bottomNavigation.setOnItemReselectedListener {
            return@setOnItemReselectedListener
        }

    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator?.vibrate(
                VibrationEffect.createOneShot(
                    60,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            //deprecated in API 26
            vibrator?.vibrate(60);
        }
    }

    override fun onBackPressed() {
        if (viewBinding.bottomNavigation.selectedItemId != R.id.homeFragment)
            viewBinding.bottomNavigation.selectedItemId = R.id.homeFragment
        super.onBackPressed()
    }

}