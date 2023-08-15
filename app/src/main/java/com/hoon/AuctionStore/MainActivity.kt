package com.hoon.AuctionStore

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : FragmentActivity() {
    private var tabs: TabLayout? = null
    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    private lateinit var fragment3: Fragment3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()

        fragmentManager.beginTransaction().add(R.id.container, fragment1).commit()

        tabs = findViewById(R.id.tabs)
        tabs?.apply {
            addTab(newTab().setText("친구"))
            addTab(newTab().setText("채팅"))
            addTab(newTab().setText("더보기"))
            addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val position = tab.position
                    val selected: Fragment = when (position) {
                        0 -> fragment1
                        1 -> fragment2
                        2 -> fragment3
                        else -> fragment1
                    }
                    fragmentManager.beginTransaction().replace(R.id.container, selected).commit()
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }
}
