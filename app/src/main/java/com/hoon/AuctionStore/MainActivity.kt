package com.hoon.AuctionStore

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : FragmentActivity() {
    private var tabs: TabLayout? = null
    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var itemListFragment: Fragment_ItemList
    private lateinit var chatFragment: Fragment_Chat
    private lateinit var myPropileFragment: Fragment_MyPropile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemListFragment = Fragment_ItemList()
        chatFragment = Fragment_Chat()
        myPropileFragment = Fragment_MyPropile()

        fragmentManager.beginTransaction().add(R.id.container, itemListFragment).commit()

        tabs = findViewById(R.id.tabs)
        tabs?.apply {
            addTab(newTab().setText("피드"))
            addTab(newTab().setText("채팅"))
            addTab(newTab().setText("상품등록"))
            addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position
                    val selected: Fragment = when (position) {
                        0 -> itemListFragment
                        1 -> chatFragment
                        2 -> myPropileFragment
                        else -> itemListFragment
                    }
                    fragmentManager.beginTransaction().replace(R.id.container, selected).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }
}
