package com.hoon.AuctionStore

import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel(){
    val shareString: MutableLiveData<String> = MutableLiveData()
    val shareList: MutableLiveData<ListView> = MutableLiveData()
    val shareGoodsDB: MutableLiveData<GoodsDB> = MutableLiveData()
}