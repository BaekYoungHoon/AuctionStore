package com.hoon.AuctionStore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLay1: Button = findViewById<Button>(R.id.btnLay1)

        btnLay1.setOnClickListener(){
            val intent = Intent(this, Lay1Activity::class.java)
            startActivity(intent)
        }
    }
}