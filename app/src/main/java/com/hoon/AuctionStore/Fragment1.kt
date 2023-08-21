package com.hoon.AuctionStore

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Fragment1 : Fragment() {
    val db = FirebaseDatabase.getInstance()
    val itemDB: DatabaseReference = db.getReference("goods")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val listView = view.findViewById<ListView>(R.id.listView)
        val itemList: MutableList<GoodsDB> = mutableListOf()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemList)

        itemDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val id = dataSnapshot.key ?: ""
                    val title = dataSnapshot.child("title").getValue(String::class.java) ?: ""
                    val price = dataSnapshot.child("price").getValue(String::class.java) ?: ""
                    Log.d("DB_READ", "title : " + title + "         price : " + price)
                    val item = GoodsDB(title, "", price)
                    Log.d("ITem", "Item : " + item)
                    itemList.add(item)
                }

                listView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed", "데이터 로딩 실패")
            }
        })


        return view
    }
}