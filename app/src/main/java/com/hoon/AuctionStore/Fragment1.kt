package com.hoon.AuctionStore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


class Fragment1 : Fragment() {
    private val itemList = listOf("Item 1", "Item 2", "Item 3") // 실제 데이터 리스트
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val listView = view.findViewById<ListView>(R.id.listView)
        val itemList = listOf("Item 1", "Item 2", "Item 3") // 실제 데이터 리스트
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemList)
        listView.adapter = adapter

        return view
    }
}