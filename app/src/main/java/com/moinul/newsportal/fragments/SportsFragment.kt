package com.moinul.newsportal.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentSportsBinding
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_sports.*


class SportsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentSportsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSportsBinding.inflate(inflater,container,false)
        newsViewModel.getSportsNews()
        binding.lifecycleOwner = this
        binding.sportsViewModel = newsViewModel



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        val newsAdapter = NewsAdapter(requireContext(), newsViewModel)

        sports_recyclerView.adapter = newsAdapter
        sports_recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            newsAdapter.setDataset(it)
        })
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.sportsRecyclerView
        recyclerView.setHasFixedSize(true)

        initialiseAdapter()
    }
    private fun initialiseAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if(verifyAvailableNetwork(requireContext())){
            Log.d("OOOOOOOOOOOOOO","INTERNET AVAILABLE")
            recyclerView.visibility = View.VISIBLE
            observeData()
        }else{
            recyclerView.visibility = View.GONE

        }

    }

    private fun observeData() {
        viewModel.articles.observe(viewLifecycleOwner){
            recyclerView.adapter = NewsAdapter(
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