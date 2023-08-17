package com.hoon.AuctionStore

import com.google.firebase.database.FirebaseDatabase

val database = FirebaseDatabase.getInstance()
class GoodsDB(title: String, price: String, detail: String) {
    var title: String = title
        private set
    var price: String = price
        private set
    var detail: String = detail
        private set
//    fun GoodsDB(title: String, price: Int, detail: String) {
//        this.title = title
//        this.price = price
//        this.detail = detail
//    }
    //fun gettitle(): String{
    //    return title
    //}
    //fun settitle(): Unit{
    //    this.title = title
    //}

    //fun getprice(): Int{
    //    return price
    //}
    //fun setprice(): Unit{
    //    this.price = price
    //}

    //fun getdetail(): String{
    //    return detail
    //}
    //fun setdetail(): Unit{
    //    this.detail = detail
    //}
}