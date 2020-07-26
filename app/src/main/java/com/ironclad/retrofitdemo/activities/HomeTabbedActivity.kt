package com.ironclad.retrofitdemo.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home_tabbed.*
import androidx.core.content.edit as edit

class HomeTabbedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_tabbed)
        setSupportActionBar(toolbarTabbed)

        val sharedPreferences = getSharedPreferences("UserID", Context.MODE_PRIVATE)
        val userId = intent.getIntExtra("userId", 0)
        val name = intent.getStringExtra("userName")
        val userName = intent.getStringExtra("userUserName")
        val editor = sharedPreferences.edit()
        editor.putInt("userID", userId)
        editor.putString("name", name)
        editor.putString("userName", userName)
        editor.apply()

        Log.d("PUI", "$userId $userName")
        supportActionBar?.title = "$name's Profile"

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Posts"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Albums"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Todos"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPagerAdapter(supportFragmentManager, tabLayout!!.tabCount)
        vpMain!!.adapter = adapter

        vpMain!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                vpMain!!.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                vpMain!!.currentItem = tab!!.position
            }

        })
    }
}