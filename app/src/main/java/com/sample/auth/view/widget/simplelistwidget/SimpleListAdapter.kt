package com.sample.auth.view.widget.simplelistwidget

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.auth.R
import com.google.android.material.button.MaterialButton

class SimpleListAdapter(private val context: Context) :
    RecyclerView.Adapter<SimpleListAdapter.SimpleListViewHolder>() {

    var items = listOf<SimpleListWidgetModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListViewHolder {
        val inflater = LayoutInflater.from(context)
        return SimpleListViewHolder(inflater.inflate(R.layout.item_simple_list, parent, false))
    }

    override fun onBindViewHolder(holder: SimpleListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    class SimpleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val iconView: MaterialButton by lazy {
            itemView.findViewById(R.id.icon)
        }

        private val titleView: MaterialButton by lazy {
            itemView.findViewById(R.id.title)
        }

        fun bind(item: SimpleListWidgetModel) {
            item.title?.let {
                titleView.text = it
            }
            item.icon?.let {
                iconView.icon = it
                iconView.visibility = VISIBLE
            }
            item.onClick?.let { onClick ->
                iconView.isEnabled = true
                titleView.isEnabled = true
                iconView.setOnClickListener {
                    onClick.invoke()
                }
                titleView.setOnClickListener {
                    iconView.performClick()
                }
            }

        }

    }

}