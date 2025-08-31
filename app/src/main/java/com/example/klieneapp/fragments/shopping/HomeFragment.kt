package com.example.klieneapp.fragments.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.klieneapp.R
import com.example.klieneapp.adapters.ViewPagerAdapter
import com.example.klieneapp.databinding.FragmentHomeBinding
import com.example.klieneapp.fragments.categories.AccessoriesFragment
import com.example.klieneapp.fragments.categories.BaseCategoryFragment
import com.example.klieneapp.fragments.categories.ChairFragment
import com.example.klieneapp.fragments.categories.CupBoardFragment
import com.example.klieneapp.fragments.categories.FurnitureFragment
import com.example.klieneapp.fragments.categories.MainFragment
import com.example.klieneapp.fragments.categories.TableFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesFragments = arrayListOf(
            MainFragment(),
            BaseCategoryFragment(),
            ChairFragment(),
            CupBoardFragment(),
            TableFragment(),
            AccessoriesFragment(),
            FurnitureFragment()
        )
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout
        val adapter = ViewPagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Base"
                2 -> tab.text = "Chair"
                3 -> tab.text = "CupBoard"
                4 -> tab.text = "Table"
                5 -> tab.text = "Accessories"
                6 -> tab.text = "Furniture"
            }
        }.attach()

    }


}