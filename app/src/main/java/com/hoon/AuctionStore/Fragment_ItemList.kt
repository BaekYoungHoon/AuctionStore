package com.hoon.AuctionStore

import android.content.Intent
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
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = itemList[position] // 클릭한 아이템의 정보 가져오기
            // 여기서 원하는 동작을 수행하면 됩니다.
            viewModel.shareGoodsDB.value = selectedItem
            viewModel.shareNum.value = position

            Log.d("SELECT_ITEM", selectedItem.toString() + "\n" + position + "\n" + viewModel.shareNum.value)
            // 예를 들어, 다른 프래그먼트로 이동하거나 다이얼로그를 띄울 수 있습니다.
            // 프래그먼트 매니저 가져오기
            val intent = Intent(requireActivity(), Activity_ItemDetail::class.java)
            // 다른 액티비티로 전환
            intent.putExtra("key", position)
            startActivity(intent)

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