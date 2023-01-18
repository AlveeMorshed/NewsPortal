package com.moinul.newsportal.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moinul.newsportal.fragments.TopNewsFragment
import com.moinul.newsportal.fragments.SportsFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){

            0 -> TopNewsFragment()
            1 -> SportsFragment()
            else -> Fragment()

        }
    }
}