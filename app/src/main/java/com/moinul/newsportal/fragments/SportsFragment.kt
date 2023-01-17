package com.moinul.newsportal.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.SportsNewsAdapter
import com.moinul.newsportal.databinding.FragmentSportsBinding
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.viewModel.NewsViewModel


class SportsFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var titleView: MaterialTextView
    private lateinit var publishDateView: MaterialTextView
    private lateinit var descriptionView: MaterialTextView
    private lateinit var newsImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSportsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.sports_recyclerView)
        recyclerView.setHasFixedSize(true)

        initialiseAdapter()
    }
    private fun initialiseAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if(verifyAvailableNetwork(requireContext())){
            recyclerView.visibility = View.VISIBLE
            observeData()
        }else{
            recyclerView.visibility = View.GONE

        }

    }

    private fun observeData() {
        viewModel.newsArticles.observe(viewLifecycleOwner){
            recyclerView.adapter = SportsNewsAdapter(
                requireContext(), viewModel, it as ArrayList<Article>

            )
        }
    }

    fun verifyAvailableNetwork(context: Context):Boolean{
        val connectivityManager= activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }


}