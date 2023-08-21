package com.hoon.AuctionStore

import android.R.attr.name
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Fragment3 : Fragment() {
    val db = FirebaseDatabase.getInstance()
    val goodsReference: DatabaseReference = db.getReference()
    val maxSerialDB: DatabaseReference = db.getReference("MaxSerial")
    lateinit var btn: Button
    lateinit var title: EditText
    lateinit var price: EditText
    lateinit var detail: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)
        btn = view.findViewById(R.id.update)
        title = view.findViewById(R.id.title)
        price = view.findViewById(R.id.price)
        detail = view.findViewById(R.id.detail)
        title.setOnClickListener(){
            title.setText("")
        }
        price.setOnClickListener(){
            price.setText("")
        }
        detail.setOnClickListener(){
            detail.setText("")
        }
        btn.setOnClickListener() {
            addGoodsDB(title.text.toString(), price.text.toString(), detail.text.toString())
            Log.d("TAG", "버튼 정상 작동")
        }
        return view
    }


    fun addGoodsDB(title: String, price: String, detail: String): Unit{
        findMaxSerial { maxSerial ->
            val serial = maxSerial.toInt() + 1 // Increment the serial
            val goodsDB = Item(title, price, detail)

            goodsReference.child("goods").child(serial.toString()).setValue(goodsDB)
            maxSerialDB.setValue(serial.toString())
//            val updateData = HashMap<String, Any>()
//            updateData["MaxSerial"] = serial // 업데이트할 MaxSerial 필드와 값 추가
//
//            maxSerialDB.updateChildren(updateData)
            Log.d("TAG", "addGoodsDB함수 호출 완료")
            Log.d("MaxSerial", "MaxSerial : " + serial)
        }
    }
    fun isNumeric(input: String): Boolean {
        val numericValue = input.toIntOrNull()
        return numericValue != null
    }
    fun findMaxSerial(callback: (String) -> Unit) {
        maxSerialDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val maxSerialValue = snapshot.getValue(String::class.java)
                if (maxSerialValue != null) {
                    callback(maxSerialValue)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // 데이터 읽기 작업이 취소되었을 때 처리할 코드
            }
        })
    }
}