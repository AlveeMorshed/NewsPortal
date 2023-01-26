package com.moinul.newsportal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentTopNewsBinding
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_top_news.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopNewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentTopNewsBinding? = null
    private val binding get() = _binding!!


    override fun onPause() {
        super.onPause()
        Log.d("TopNewsFragment", "onPause called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("TopNewsFragment", "onCreateView called")
        _binding = FragmentTopNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        binding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchNewsByCategory("top-US")
            swipeRefreshLayout.isRefreshing = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TopNewsFragment", "onViewCreated called")
        recyclerView = binding.recyclerViewTopUS
        recyclerView.setHasFixedSize(true)
        initialiseAdapter()
    }
    private fun initialiseAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        observeData()
    }
    private fun observeData() {
        viewModel.readAllTopNews.observe(viewLifecycleOwner) {
            val adapterScrollState = recyclerView.layoutManager?.onSaveInstanceState()
            recyclerView.layoutManager?.onRestoreInstanceState(adapterScrollState)
            recyclerView.adapter = NewsAdapter(requireContext(), viewModel, it as ArrayList<ArticleForRoomDB>)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("TopNewsFragment", "onStop called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TopNewsFragment", "onResume called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TopNewsFragment", "onSaveInstanceState called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TopNewsFragment", "onDestroyView called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TopNewsFragment", "onDestroy called")
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Log.d("TopNewsFragment", "onStart called")
    }
}