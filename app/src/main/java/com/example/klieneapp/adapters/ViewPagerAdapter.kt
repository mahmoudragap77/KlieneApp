package com.example.klieneapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.klieneapp.fragments.categories.BaseCategoryFragment
import com.example.klieneapp.fragments.categories.MainFragment
import com.example.klieneapp.fragments.shopping.CartFragment
import com.example.klieneapp.fragments.shopping.HomeFragment
import com.example.klieneapp.fragments.shopping.ProfileFragment
import com.example.klieneapp.fragments.shopping.SearchFragment

class ViewPagerAdapter(
    private val fragment: List<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }

    override fun getItemCount()=fragment.size

}