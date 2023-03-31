package com.monstarlab.signup.view.widget.simplelistwidget

import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.monstarlab.signup.R
import com.monstarlab.signup.view.widget.BaseWidget

class SimpleListWidget(
    context: Context,
    attributeSet: AttributeSet
) :
    BaseWidget(context, attributeSet, R.layout.widget_simple_list) {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: SimpleListAdapter

    override fun initLayout(context: Context, attr: AttributeSet) {
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = SimpleListAdapter(context)
        recyclerView.adapter = adapter
    }

    companion object {

        @BindingAdapter("list_data")
        @JvmStatic
        fun setList(view: SimpleListWidget, data: List<SimpleListWidgetModel>) {
            view.setList(data)
        }

    }

    fun setList(data: List<SimpleListWidgetModel>) {
        adapter.items = data
    }

}