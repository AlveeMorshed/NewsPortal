package com.moinul.newsportal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.ViewPagerAdapter
import com.moinul.newsportal.databinding.ActivityMainBinding
import com.moinul.newsportal.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding :FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2
        viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2){
                tab,position ->

            when(position){
                0 -> tab.text="BOOKMARKS"
                1 -> tab.text="SPORTS"
            }
        }.attach()
    }


}