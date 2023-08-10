package com.hoon.AuctionStore
import android.app.Application
import com.google.firebase.FirebaseApp

val database = FirebaseDatabase.getInstance()
class DB : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}