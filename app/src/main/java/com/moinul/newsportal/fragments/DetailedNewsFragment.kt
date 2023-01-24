package com.moinul.newsportal.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.moinul.newsportal.R
import com.moinul.newsportal.databinding.FragmentDetailedNewsBinding
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.util.Constants
import com.moinul.newsportal.viewModel.NewsViewModel

class DetailedNewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private var _binding: FragmentDetailedNewsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailedNewsFragmentArgs by navArgs()

    var currentNews: ArticleForRoomDB? = null

    /*companion object {
        fun newInstance(article: ArticleForRoomDB) = DetailedNewsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("article", article)
            }
        }
    }*/




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailedNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragment?.setHasOptionsMenu(false)
        currentNews = args.news

        Glide.with(requireContext())
            .load(currentNews?.urlToImage)
            .error(R.drawable.ic_connection_error)
            .into(binding.newsImage)

        binding.title.text = currentNews?.title.toString()
        if(currentNews?.title.toString()=="null" || currentNews?.title==null || currentNews?.title.toString()==""){

        }
        binding.author.text = currentNews?.author.toString()
        binding.description.text = currentNews?.description.toString()
        binding.content.text = currentNews?.content.toString()

        binding.readMoreButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.ARTICLE_URL, currentNews?.url)
            view.findNavController().navigate(R.id.webViewFragment, bundle)
        }

    }

}