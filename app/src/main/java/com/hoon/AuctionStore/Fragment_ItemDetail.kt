package com.hoon.AuctionStore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Fragment_ItemDetail : Fragment() {
    val db = FirebaseDatabase.getInstance()
    val itemDB: DatabaseReference = db.getReference("goods")
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.itemdetail_activity, container, false)

        val titleT: TextView = view.findViewById(R.id.title)
        val priceT: TextView = view.findViewById(R.id.price)
        val directT: TextView = view.findViewById(R.id.direct)
        val detailT: TextView = view.findViewById(R.id.detail)

        itemDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val title = dataSnapshot.child("title").getValue(String::class.java) ?: ""
                    val price = dataSnapshot.child("price").getValue(String::class.java) ?: ""
                    val direct = dataSnapshot.child("direct").getValue(String::class.java) ?: ""
                    val detail = dataSnapshot.child("detail").getValue(String::class.java) ?: ""

                    Log.d("DB_READ", "title : " + title + "         price : " + price + "       direct : " + direct)
                    val item = GoodsDB(title, "", price, direct)
                    if (viewModel.shareGoodsDB.value == item) {
                        titleT.setText(title)
                        priceT.setText(price)
                        directT.setText(direct)
                        detailT.setText(detail)
                    }
                    Log.d("ITem", "Item : " + item)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed", "데이터 로딩 실패")
            }
        })

        return view
    }
}
