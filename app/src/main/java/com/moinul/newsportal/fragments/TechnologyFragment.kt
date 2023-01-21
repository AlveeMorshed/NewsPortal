package com.moinul.newsportal.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentGeneralBinding
import com.moinul.newsportal.databinding.FragmentTechnologyBinding
import com.moinul.newsportal.viewModel.NewsViewModel


class TechnologyFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentTechnologyBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTechnologyBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this


        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
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
        val newsAdapter = NewsAdapter(requireContext(), viewModel, viewLifecycleOwner)
        recyclerView.adapter = newsAdapter
        viewModel.getNewsFromDB("technology").observe(viewLifecycleOwner){
            newsAdapter.setDataset(it)
        }

    }

    fun verifyAvailableNetwork(context: Context):Boolean{
        val connectivityManager= activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}
