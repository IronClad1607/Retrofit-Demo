package com.ironclad.retrofitdemo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ironclad.retrofitdemo.fragments.AlbumFragment
import com.ironclad.retrofitdemo.fragments.PostFragment
import com.ironclad.retrofitdemo.fragments.TodoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, private var totalTabs: Int) :
    FragmentPagerAdapter(fragmentManager, totalTabs) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PostFragment()
            1 -> AlbumFragment()
            2 -> TodoFragment()
            else -> Fragment()
        }
    }

    override fun getCount() = totalTabs

}