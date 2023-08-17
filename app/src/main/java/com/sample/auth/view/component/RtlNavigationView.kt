package com.sample.auth.view.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Menu
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.forEach
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView
import com.sample.auth.R

class RtlNavigationView : NavigationView {

    lateinit var rtlMenu: Menu

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val array =
            context.obtainStyledAttributes(attrs, intArrayOf(R.attr.rtl_menu))

        if (array.getResourceId(0, -1) != -1) {
            inflateRtlMenu(context, array.getResourceId(0, -1))
            array.recycle()
        }

    }

    @SuppressLint("RestrictedApi")
    private fun inflateRtlMenu(context: Context, resourceId: Int) {
        menu.clear()
        rtlMenu = NavigationMenu(context)
        SupportMenuInflater(context).inflate(resourceId, rtlMenu)
        createRtlMenu(rtlMenu)
    }

    private fun createRtlMenu(rtlMenu: Menu) {
        rtlMenu.forEach {

            val ltrItem = menu.add(null)
            val group = it.hasSubMenu()
            ltrItem.setActionView(R.layout.rtl_menu_item)

            val imgIcon = ltrItem.actionView.findViewById<AppCompatImageView>(R.id.icon)
            val txtTitle = ltrItem.actionView.findViewById<AppCompatTextView>(R.id.title)

            if (group) {
                txtTitle.text = it.title
                txtTitle.visibility = VISIBLE
                createRtlMenu(it.subMenu)
            } else {
                txtTitle.text = it.title
                txtTitle.visibility = VISIBLE
                imgIcon.setImageDrawable(it.icon)
                imgIcon.visibility = VISIBLE
            }

        }
    }

}