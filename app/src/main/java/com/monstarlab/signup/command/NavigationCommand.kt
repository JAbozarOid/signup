package com.monstarlab.signup.command

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.monstarlab.signup.R

abstract class NavigationCommand() {

    abstract fun navigate(activity: Activity)

    protected var actionId = 0
    protected var data: Bundle? = null
    protected var activityClass: Class<out AppCompatActivity>? = null
    protected var flags: Int = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    protected var requestCode: Int? = null
    protected var finishSourceActivity = false
    protected var isInclusive = true

    constructor(actionId: Int, data: Bundle? = null, isInclusive: Boolean = true) : this() {
        this.actionId = actionId
        this.data = data
        this.isInclusive = isInclusive
    }

    constructor(
        activityClass: Class<out AppCompatActivity>,
        data: Bundle? = null,
        flags: Int = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK,
        requestCode: Int? = null,
        finishSourceActivity: Boolean = false
    ) : this() {
        this.activityClass = activityClass
        this.data = data
        this.flags = flags
        this.requestCode = requestCode
        this.finishSourceActivity = finishSourceActivity
    }

    companion object {
        var defaultNavOptionBuilder = NavOptions.Builder()
    }

    class To(
        actionId: Int,
        data: Bundle? = null,
        isInclusive: Boolean = true,
        private val navOption: NavOptions.Builder? = null
    ) :
        NavigationCommand(actionId = actionId, data = data, isInclusive = isInclusive) {
        override fun navigate(activity: Activity) {
            val option = navOption ?: defaultNavOptionBuilder
            Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(
                actionId,
                if (data != null) data else null,
                option.setPopUpTo(actionId, isInclusive).build()
            )
        }

    }

    class Back : NavigationCommand() {
        override fun navigate(activity: Activity) {
            Navigation.findNavController(activity, R.id.nav_host_fragment).popBackStack()
        }

    }

    class Home : NavigationCommand() {
        override fun navigate(activity: Activity) {
            Navigation.findNavController(activity, R.id.nav_host_fragment).navigateUp()
        }

    }

    class ToActivity(
        activityClass: Class<out AppCompatActivity>,
        data: Bundle? = null,
        flags: Int = 0,
        requestCode: Int? = null,
        finishSourceActivity: Boolean = false
    ) : NavigationCommand(activityClass, data, flags, requestCode, finishSourceActivity) {

        override fun navigate(activity: Activity) {
            val intent = Intent(activity, activityClass)
            if (data != null) intent.putExtras(data!!)
            intent.flags = flags
            if (requestCode != null) activity.startActivityForResult(
                intent,
                requestCode!!
            ) else activity.startActivity(intent)
            if (finishSourceActivity) activity.finish()
        }

    }
}

