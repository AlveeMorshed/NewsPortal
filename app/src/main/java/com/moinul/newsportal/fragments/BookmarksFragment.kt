package com.moinul.newsportal.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.BookmarkAdapter
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentBookmarksBinding
import com.moinul.newsportal.databinding.FragmentBusinessBinding
import com.moinul.newsportal.databinding.FragmentHomeBinding
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.viewModel.BookmarkViewModel
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import kotlinx.android.synthetic.main.fragment_business.view.*
import kotlinx.android.synthetic.main.fragment_entertainment.view.*
import kotlinx.android.synthetic.main.fragment_general.view.*
import kotlinx.android.synthetic.main.fragment_health.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_science.view.*
import kotlinx.android.synthetic.main.fragment_sports.view.*
import kotlinx.android.synthetic.main.fragment_technology.view.*
import kotlinx.android.synthetic.main.fragment_top_news.view.*

class BookmarksFragment : Fragment() {
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                /*if (query != null) {

                    if(viewPager2!=null && viewPager2.currentItem==0){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_topUS?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true
                    }
                    else if(viewPager2!=null && viewPager2.currentItem==1){
                        Log.d(TAG,"${viewPager2.currentItem}")
                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_business?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==2){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_entertainment?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==3){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_general?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==4){

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_health?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==5) {

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_science?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==6) {

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_sports?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }else if(viewPager2!=null && viewPager2.currentItem==7) {

                        val childFragment = viewPager2.getChildAt(0)
                        Log.d(TAG,"ChildFragment ki? : ${childFragment.toString()}")
                        val adapter = childFragment?.recyclerView_technology?.adapter as NewsAdapter
                        adapter.performSearch(query)
                        return true

                    }

                }*/
                return false
            }override fun onQueryTextChange(newText: String?): Boolean {


                if (newText != null) {
                    val adapter = recyclerView_bookmark.adapter as BookmarkAdapter?
                    adapter?.performSearch(newText)
                    return true
                }
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarksBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerViewBookmark
        recyclerView.setHasFixedSize(true)
        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if(MainActivity.checkConnectivity(requireContext())){
            Log.d("OOOOOOOOOOOOOO","INTERNET AVAILABLE")
            recyclerView.visibility = View.VISIBLE
            observeData()
        }else{
            recyclerView.visibility = View.GONE

        }

    }

    private fun observeData() {
        /*val newsAdapter = NewsAdapter(requireContext(), viewModel, this)
        recyclerView.adapter = newsAdapter*/
        viewModel.getBookmarkFromDB().observe(viewLifecycleOwner){
            //newsAdapter.setDataset(it)
            recyclerView.adapter = BookmarkAdapter(requireContext(), viewModel, it as ArrayList<Bookmark>)
        }

    }


}