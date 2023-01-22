package com.moinul.newsportal.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentTopNewsBinding
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_top_news.*

class TopNewsFragment : Fragment() {
    private lateinit var  viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentTopNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopNewsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this


        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        binding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchTopNews()
            swipeRefreshLayout.isRefreshing = false
        }
        //binding.allNewsViewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)

        initialiseAdapter()
//        recyclerView = binding.recyclerView
//        recyclerView.setHasFixedSize(true)
//
//        initialiseAdapter()
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
        val newsAdapter = NewsAdapter(requireContext(), viewModel, viewLifecycleOwner)
        recyclerView.adapter = newsAdapter
        /*viewModel.getNewsFromDB("top-US").observe(viewLifecycleOwner){ val it1 = it
            *//*for(currentArticle in it1){
                viewModel.isBookmarked(currentArticle.id).observe(viewLifecycleOwner){val it2 = it
                    currentArticle.bookmarked = it2
                }
            }*//*
            newsAdapter.setDataset(it)
        }*/
        viewModel.readAllTopNews.observe(viewLifecycleOwner){ val it1 = it
            /*for(currentArticle in it1){
                viewModel.isBookmarked(currentArticle.id).observe(viewLifecycleOwner){val it2 = it
                    currentArticle.bookmarked = it2
                }
            }*/
            newsAdapter.setDataset(it)
        }
    }

    fun verifyAvailableNetwork(context: Context):Boolean{
        val connectivityManager= activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }


}