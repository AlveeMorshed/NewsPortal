package com.moinul.newsportal.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.R
import com.moinul.newsportal.adapters.NewsAdapter
import com.moinul.newsportal.databinding.FragmentTopNewsBinding
import com.moinul.newsportal.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_top_news.*

class TopNewsFragment() : Fragment() {
    private lateinit var  viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentTopNewsBinding? = null
    private val binding get() = _binding!!

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        Log.d("TopNewsFragment","onCreate called")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search ->{

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }*/

    override fun onPause() {
        super.onPause()
        Log.d("TopNewsFragment","onPause called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("TopNewsFragment","onCreateView called")
        _binding = FragmentTopNewsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this


        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        binding.swipeRefreshLayout.setOnRefreshListener {


            swipeRefreshLayout.isRefreshing= true
            viewModel.fetchTopNews()
            Handler().postDelayed({
                // code to execute after the specified delay
                swipeRefreshLayout.isRefreshing = false
            }, 3000)

            //swipeRefreshLayout.isRefreshing = false
        }
        //binding.allNewsViewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TopNewsFragment","onViewCreated called")
        recyclerView = binding.recyclerViewTopUS
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
        val newsAdapter = NewsAdapter(requireContext(), viewModel, this)
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

    override fun onStop() {
        super.onStop()
        Log.d("TopNewsFragment", "onStop called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TopNewsFragment","onResume called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TopNewsFragment","onSaveInstanceState called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TopNewsFragment","onDestroyView called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TopNewsFragment","onDestroy called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TopNewsFragment","onStart called")
    }


    fun verifyAvailableNetwork(context: Context):Boolean{
        val connectivityManager= activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }


}