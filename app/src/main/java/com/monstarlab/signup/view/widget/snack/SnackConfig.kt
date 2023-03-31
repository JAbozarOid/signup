package com.monstarlab.signup.view.widget.snack

import android.view.View

class SnackConfig(
    val message:String,
    val action: Pair<String,OnActionListener>? = null
){
    companion object{
        fun getClickedOption(message:String,action:()->Unit) =
        Pair<String,OnActionListener>(message,object : OnActionListener{
            override fun onClick(p0: View?) {
                action()
            }
        })

    }
    interface OnActionListener: View.OnClickListener
}