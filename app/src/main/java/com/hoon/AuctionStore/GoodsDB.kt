package com.hoon.AuctionStore

import com.google.firebase.database.FirebaseDatabase

data class GoodsDB(val title: String, val detail: String, val price: String) {
    override fun toString(): String {
        return "제목: $title\n설명: $detail\n가격: $price"
    }
}
//val database = FirebaseDatabase.getInstance()
//class GoodsDB(title: String, price: String, detail: String) {
//    var title: String = title
//        private set
//    var price: String = price
//        private set
//    var detail: String = detail
//        private set
//
////    constructor(title: String, price: String): this(title, price,""){
////        this.title = title
////        this.price = price
////    }
//
//}