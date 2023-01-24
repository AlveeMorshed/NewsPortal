package com.moinul.newsportal.fragments
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.R

import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentBusinessBinding
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_top_news.*


class BusinessFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this


        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        binding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchNewsByCategory("business")
            swipeRefreshLayout.isRefreshing = false
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerViewBusiness
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
        viewModel.getNewsFromDB("business").observe(viewLifecycleOwner){
            //newsAdapter.setDataset(it)
            recyclerView.adapter = NewsAdapter(requireContext(), viewModel, it as ArrayList<ArticleForRoomDB>)
        }

    }


}
