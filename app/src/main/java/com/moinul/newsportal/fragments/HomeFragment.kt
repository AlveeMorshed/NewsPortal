package com.moinul.newsportal.fragments

import android.os.Build.VERSION_CODES.N
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.adapters.ViewPagerAdapter
import com.moinul.newsportal.databinding.ActivityMainBinding
import com.moinul.newsportal.databinding.FragmentHomeBinding
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.network.NewsApi
import com.moinul.newsportal.viewModel.BookmarkViewModel
import com.moinul.newsportal.viewModel.NewsViewModel


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

                0 -> tab.text="TOP NEWS"
                1 -> tab.text="SPORTS"
            }
        }.attach()
    }


}