package com.moinul.newsportal.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.model.NewsDatabase
import com.moinul.newsportal.network.NewsApi
import com.moinul.newsportal.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): AndroidViewModel(application) {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _sportsNews = MutableLiveData<List<Article>>()
    val sportsNews: LiveData<List<Article>> = _sportsNews



    val readAllData: LiveData<List<Article>>
    val repository: ArticleRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).getDao()
        repository = ArticleRepository(newsDao)
        readAllData = repository.readAllArticle
        getTopNews()
        getSportsNews()
    }

    fun addArticle(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }

    fun getTopNews(){
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getTopNews().articles
                for (i in 0..articles.value!!.size){
                    addArticle(articles.value!![i])
                }
            }catch (e:Exception){
                Log.d("000000000000000","$e     $articles")
                _articles.value = listOf()
            }
        }
    }

    fun getSportsNews(){
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getSportsNews().articles
                for (i in 0..articles.value!!.size){
                    addArticle(articles.value!![i])
                }
            }catch (e:Exception){
                Log.d("000000000000000","$e     $articles")
                _articles.value = listOf()
            }
        }
    }

}