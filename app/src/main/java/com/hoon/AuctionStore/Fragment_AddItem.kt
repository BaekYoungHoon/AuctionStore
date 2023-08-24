package com.hoon.AuctionStore

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Fragment_AddItem : AppCompatActivity() {
    val db = FirebaseDatabase.getInstance()
    val goodsReference: DatabaseReference = db.getReference()
    val maxSerialDB: DatabaseReference = db.getReference("MaxSerial")
    lateinit var btn: Button
    lateinit var title: EditText
    lateinit var price: EditText
    lateinit var detail: EditText
    lateinit var direct: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additem_fragment)

        btn = findViewById(R.id.update)
        title = findViewById(R.id.title)
        price = findViewById(R.id.price)
        detail = findViewById(R.id.detail)
        direct = findViewById(R.id.direct)

        title.setOnClickListener {
            title.setText("")
        }
        price.setOnClickListener {
            price.setText("")
        }
        detail.setOnClickListener {
            detail.setText("")
        }
        btn.setOnClickListener {
            addGoodsDB(title.text.toString(), price.text.toString(), direct.text.toString(), detail.text.toString())
            title.setText("")
            price.setText("")
            direct.setText("")
            detail.setText("")
            Log.d("TAG", "버튼 정상 작동")
        }

//        val btnMain: Button = findViewById<Button>(R.id.btnMain)
//
//        btnMain.setOnClickListener(){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }
    fun addGoodsDB(title: String, price: String, direct: String, detail: String) {
        findMaxSerial { maxSerial ->
            val serial = maxSerial.toInt() + 1 // Increment the serial
            val goodsDB = Item(title, price, direct, detail)

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
