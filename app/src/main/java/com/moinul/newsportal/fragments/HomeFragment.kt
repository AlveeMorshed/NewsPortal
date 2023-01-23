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
import kotlinx.android.synthetic.main.fragment_health.*
import kotlinx.android.synthetic.main.fragment_health.recyclerView
import kotlinx.android.synthetic.main.fragment_home.*
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
                        currentCategory = "top-US"
                    }
                1 -> {tab.text="Business"
                    currentCategory ="business"
                }
                2 -> {
                    tab.text="Entertainment"
                    currentCategory = "entertainment"
                }
                3 -> {
                    tab.text="General"
                    currentCategory = "entertainment"
                }
                4 -> {
                    tab.text="Health"
                    currentCategory = "health"
                }
                5 -> {
                    tab.text="Science"
                    currentCategory = "science"
                }
                6 -> {tab.text="Sports"
                    currentCategory = "sports"
                }
                7 -> {
                    tab.text="Technology"
                    currentCategory = "technology"
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
        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView



        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "query: $newText")
                //Log.d(TAG,"${viewPager2.currentItem.toString()}")
                Log.d("category ki", "$currentCategory")
                if (newText != null) {
                    //val adapter = recyclerView.adapter as NewsAdapter
                    Log.d("category ki", "$currentCategory")
                    if(viewPager2!=null && viewPager2.currentItem==0){
                        Log.d("category ki", "$currentCategory")
                        val childFragment = viewPager2.getChildAt(0)
                        val adapter = childFragment.recyclerView_topUS.adapter as NewsAdapter
                        adapter.performSearch(newText)

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