package com.moinul.newsportal.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moinul.newsportal.fragments.*


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    var currentFragment: Fragment? = null
    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){

            0 -> {
                currentFragment = TopNewsFragment()
                return currentFragment as TopNewsFragment
            }
            1 -> {
                currentFragment = BusinessFragment()
                return currentFragment as BusinessFragment
            }
            2 -> {
                currentFragment = EntertainmentFragment()
                return currentFragment as EntertainmentFragment
            }
            3 -> {
                currentFragment = GeneralFragment()
                return currentFragment as GeneralFragment
            }
            4 -> {
                currentFragment = HealthFragment()
                return currentFragment as HealthFragment
            }
            5 -> {
                currentFragment = ScienceFragment()
                return currentFragment as ScienceFragment
            }
            6 -> {
                currentFragment = SportsFragment()
                return currentFragment as SportsFragment
            }
            7 -> {
                currentFragment = TechnologyFragment()
                return currentFragment as TechnologyFragment
            }
            else -> {
                currentFragment = Fragment()
                return currentFragment as Fragment
            }

        }
    }
}