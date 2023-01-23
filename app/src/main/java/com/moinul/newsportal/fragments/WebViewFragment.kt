package com.moinul.newsportal.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.moinul.newsportal.R
import com.moinul.newsportal.databinding.FragmentDetailedNewsBinding
import com.moinul.newsportal.databinding.FragmentWebViewBinding
import com.moinul.newsportal.viewModel.NewsViewModel

class WebViewFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    var currentUrl : String? = null



    private val args: WebViewFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentUrl = args.articleUrl
        binding.articleWebView.apply {
            loadUrl(currentUrl.toString())
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

    }


}