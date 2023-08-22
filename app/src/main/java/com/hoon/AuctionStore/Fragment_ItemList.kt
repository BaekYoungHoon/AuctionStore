package com.hoon.AuctionStore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Fragment_ItemList : Fragment() {
    val db = FirebaseDatabase.getInstance()
    val itemDB: DatabaseReference = db.getReference("goods")
    private lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.itemlist_fragment, container, false)

        val listView = view.findViewById<ListView>(R.id.listView)
        val itemList: MutableList<GoodsDB> = mutableListOf()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = itemList[position] // 클릭한 아이템의 정보 가져오기
            // 여기서 원하는 동작을 수행하면 됩니다.
            viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            viewModel.shareGoodsDB.value = selectedItem
            // 예를 들어, 다른 프래그먼트로 이동하거나 다이얼로그를 띄울 수 있습니다.
            // 프래그먼트 매니저 가져오기
            val fragmentManager = requireActivity().supportFragmentManager// 프래그먼트 트랜잭션 시작
            val transaction = fragmentManager.beginTransaction() // 프래그먼트 인스턴스 생성
            val fragment = Fragment_ItemDetail()
            // YourFragment에는 원하는 프래그먼트 클래스를 넣어줘야 합니다.
            // 프래그먼트 추가 또는 교체
            transaction.replace(R.id.itemdetail_fragment, fragment) // fragmentContainer는 프래그먼트를 표시할 뷰의 ID입니다.
            transaction.addToBackStack(null) // 뒤로 가기 버튼으로 이전 프래그먼트로 돌아갈 수 있도록 추가합니다.
            transaction.commit() // 트랜잭션을 커밋하여 변경사항을 적용합니다.
        }

        itemDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val title = dataSnapshot.child("title").getValue(String::class.java) ?: ""
                    val price = dataSnapshot.child("price").getValue(String::class.java) ?: ""
                    val direct = dataSnapshot.child("direct").getValue(String::class.java) ?: ""
                    Log.d("DB_READ", "title : " + title + "         price : " + price + "       direct : " + direct)
                    val item = GoodsDB(title, "", price, direct)
                    Log.d("ITem", "Item : " + item)
                    itemList.add(item)
                }

                listView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed", "데이터 로딩 실패")
            }
        })


        return view
    }
}