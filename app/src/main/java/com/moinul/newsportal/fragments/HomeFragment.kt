package com.moinul.newsportal.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.adapters.ViewPagerAdapter
import com.moinul.newsportal.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_business.view.*
import kotlinx.android.synthetic.main.fragment_entertainment.view.*
import kotlinx.android.synthetic.main.fragment_general.view.*
import kotlinx.android.synthetic.main.fragment_health.*
import kotlinx.android.synthetic.main.fragment_health.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_science.view.*
import kotlinx.android.synthetic.main.fragment_sports.view.*
import kotlinx.android.synthetic.main.fragment_technology.view.*
import kotlinx.android.synthetic.main.fragment_top_news.*
import kotlinx.android.synthetic.main.fragment_top_news.view.*

private const val TAG = "HomeFragment"
class HomeFragment : Fragment() {
    private var _binding :FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var currentCategory: String = ""
    var menu:Menu? = null

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
                0 -> {
                        tab.text="TOP US NEWS"
                        tab.setIcon(R.drawable.icon_top_news)
                    }
                1 -> {
                    tab.text="Business"
                    tab.setIcon(R.drawable.icon_business_news)
                }
                2 -> {
                    tab.text="Entertainment"
                    tab.setIcon(R.drawable.icon_entertainment)

                }
                3 -> {
                    tab.text="General"
                    tab.setIcon(R.drawable.icon_general)
                }
                4 -> {
                    tab.text="Health"
                    tab.setIcon(R.drawable.icon_health)
                }
                5 -> {
                    tab.text="Science"
                    tab.setIcon(R.drawable.icon_science)
                }
                6 -> {
                    tab.text="Sports"
                    tab.setIcon(R.drawable.icon_sports)
                }
                7 -> {
                    tab.text = "Technology"
                    tab.setIcon(R.drawable.icon_technology)
                }
            }
        }.attach()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView



        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                if (newText != null) {

                    if(viewPager2!=null && viewPager2.currentItem==0){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_topUS?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true
                    }
                    else if(viewPager2!=null && viewPager2.currentItem==1){
                        Log.d(TAG,"${viewPager2.currentItem}")
                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_business?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==2){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_entertainment?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==3){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_general?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==4){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_health?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==5) {

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_science?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==6) {

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_sports?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==7) {

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_technology?.adapter as NewsAdapter?
                        adapter?.performSearch(newText)
                        return true

                    }

                }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }




}