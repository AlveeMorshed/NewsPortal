package com.moinul.newsportal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.moinul.newsportal.adapters.ViewPagerAdapter
import com.moinul.newsportal.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding :FragmentHomeBinding? = null
    private val binding get() = _binding!!


    //private lateinit var bookmarkViewModel: BookmarkViewModel


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


        //bookmarkViewModel = ViewModelProvider(this)[BookmarkViewModel::class.java]


        //val bookmarkAdapter = NewsAdapter(requireContext(), bookmarkViewModel)



        val viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2
        viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2){
                tab,position ->

            when(position){
                0 -> tab.text="TOP US NEWS"
                1 -> tab.text="Business"
                2 -> tab.text="Entertainment"
                3 -> tab.text="General"
                4 -> tab.text="Health"
                5 -> tab.text="Science"
                6 -> tab.text="Sports"
                7 -> tab.text="Technology"
            }
        }.attach()
    }


}