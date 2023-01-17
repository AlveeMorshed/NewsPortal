package com.moinul.newsportal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.network.NewsApi
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    private val _newsArticles = MutableLiveData<List<Article>>()
    val newsArticles: LiveData<List<Article>> = _newsArticles

    private fun getSportsNews(){
        viewModelScope.launch{
            try {
                _newsArticles.value = NewsApi.retrofitService.getSportsNews()
            }catch (e:Exception){
                _newsArticles.value = listOf()
            }
        }
    }

}