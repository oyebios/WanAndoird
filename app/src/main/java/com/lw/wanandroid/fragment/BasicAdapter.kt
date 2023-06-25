package com.lw.wanandroid.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lw.wanandroid.R

class BasicAdapter(list: MutableList<String>) : RecyclerView.Adapter<BasicAdapter.ViewHolder>() {
    var dataList: MutableList<String> = list

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1: TextView? = null

        init {
            tv1 = itemView.findViewById(R.id.id_tv1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_basic, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList.get(position)
        holder.tv1?.text = data
    }
}