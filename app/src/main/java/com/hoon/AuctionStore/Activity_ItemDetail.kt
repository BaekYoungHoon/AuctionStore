package com.hoon.AuctionStore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Activity_ItemDetail : AppCompatActivity() {
    val db = FirebaseDatabase.getInstance()
    val itemDB: DatabaseReference = db.getReference("goods")
    private lateinit var viewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemdetail_activity)

        val titleT: TextView = findViewById(R.id.title)
        val priceT: TextView = findViewById(R.id.price)
        val directT: TextView = findViewById(R.id.direct)
        val detailT: TextView = findViewById(R.id.detail)
        val btn: Button = findViewById(R.id.back)
        viewModel = ViewModelProvider(this@Activity_ItemDetail).get(SharedViewModel::class.java)
        val listNum: Int = viewModel.shareNum.value!!.plus(1)

        btn.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            // 다른 액티비티로 전환
            startActivity(intent)
        }
        itemDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val title = dataSnapshot.child("title").getValue(String::class.java) ?: ""
                    val price = dataSnapshot.child("price").getValue(String::class.java) ?: ""
                    val direct = dataSnapshot.child("direct").getValue(String::class.java) ?: ""
                    val detail = dataSnapshot.child("detail").getValue(String::class.java) ?: ""
                    Log.d("DATASNAP", "dataSnapshot = " + dataSnapshot + "          스냅샷칠드런 = " + snapshot.children)
                    Log.d("DB_READ", "title : " + title + "         price : " + price + "       direct : " + direct)
                    Log.d("ListNum", "ListNum = " + listNum)
                    val item = GoodsDB(title, "", price, direct)
                    if(listNum.toString() == dataSnapshot.toString()){
                        titleT.setText(title)
                        priceT.setText(price)
                        directT.setText(direct)
                        detailT.setText(detail)
                        Log.d("FIND", "데이터 일치")
                    }
                    Log.d("ITem", "Item : " + item)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed", "데이터 로딩 실패")
            }
        })
    }
}