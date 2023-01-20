package com.moinul.newsportal.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moinul.newsportal.fragments.*


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){

            0 -> TopNewsFragment()
            1 -> BusinessFragment()
            2 -> EntertainmentFragment()
            3 -> GeneralFragment()
            4 -> HealthFragment()
            5 -> ScienceFragment()
            6 -> SportsFragment()
            7 -> TechnologyFragment()
            else -> Fragment()

        }
    }
}