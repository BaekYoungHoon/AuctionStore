package com.hoon.AuctionStore

data class GoodsDB(var title: String, var detail: String, var price: String, var direct: String) {
    override fun toString(): String {
        return "제목: $title\n현재 경매가: $price\n즉시 구매가: $direct"
    }
}
// val database = FirebaseDatabase.getInstance()
// class GoodsDB(title: String, price: String, detail: String) {
//    var title: String = title
//        private set
//    var price: String = price
//        private set
//    var detail: String = detail
//        private set
//
// //    constructor(title: String, price: String): this(title, price,""){
// //        this.title = title
// //        this.price = price
// //    }
//
// }
