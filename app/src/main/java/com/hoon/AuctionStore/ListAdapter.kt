package com.hoon.AuctionStore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(private val context: Context, private val itemList: List<String>) : BaseAdapter() {

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Any = itemList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.list_item_layout, parent, false)

        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
        itemTitle.text = itemList[position]

        return itemView
    }
}
