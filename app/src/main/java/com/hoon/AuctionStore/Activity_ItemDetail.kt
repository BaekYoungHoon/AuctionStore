package com.hoon.AuctionStore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
        val ChangePrice: EditText = findViewById(R.id.changeprice)

        viewModel = ViewModelProvider(this@Activity_ItemDetail).get(SharedViewModel::class.java)
        val ListNum = intent.extras?.getInt("key")?.plus(1) // 데이터 추출

        var serials: String = ""
        var title: String = ""
        var price: String = ""
        var direct: String = ""
        var detail: String = ""

        fun isNumeric(input: String): Boolean {
            return input.toIntOrNull() != null
        }
        ChangePrice.setOnClickListener {
            ChangePrice.setText("")
        }
        btn.setOnClickListener {
            val priceE = ChangePrice.text.toString().toInt()
            val priceI = price.toInt()
            if (priceE > priceI) {
                if (isNumeric(ChangePrice.text.toString())) {
                    startActivity(intent)
                }
                price = ChangePrice.text.toString()
                itemDB.child(serials).setValue(GoodsDB(title, detail, price, direct))
            }


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        itemDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    serials = dataSnapshot.key ?: ""
                    title = dataSnapshot.child("title").getValue(String::class.java) ?: ""
                    price = dataSnapshot.child("price").getValue(String::class.java) ?: ""
                    direct = dataSnapshot.child("direct").getValue(String::class.java) ?: ""
                    detail = dataSnapshot.child("detail").getValue(String::class.java) ?: ""
                    Log.d("DATASNAP", "dataSnapshot = " + dataSnapshot + "          스냅샷칠드런 = " + snapshot.children)
                    Log.d("DB_READ", "title : " + title + "         price : " + price + "       direct : " + direct)
                    Log.d("ListNum", "ListNum = " + serials)
                    Log.d("receivedData", "$ListNum")
                    val item = GoodsDB(title, "", price, direct)
                    if (ListNum.toString() == serials) {
                        titleT.setText(title)
                        priceT.setText(price)
                        directT.setText(direct)
                        detailT.setText(detail)
                        Log.d("FIND", "데이터 일치\n $serials")
                        break
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
