package com.moinul.newsportal.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.R
import com.moinul.newsportal.model.*
import com.moinul.newsportal.network.NewsApi
import com.moinul.newsportal.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): AndroidViewModel(application) {
    val repository: ArticleRepository

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _topNews = MutableLiveData<List<Article>>()
    val topNews: LiveData<List<Article>> = _topNews

    private val _sportsNews = MutableLiveData<List<Article>>()
    val sportsNews: LiveData<List<Article>> = _sportsNews



    var readAllData: LiveData<List<ArticleForRoomDB>>

    var readAllTopNews : LiveData<List<ArticleForRoomDB>>


    init {
        val newsDao = NewsDatabase.getDatabase(application).getDao()
        //fetchAllNews()
        repository = ArticleRepository(newsDao)
        readAllData = repository.readAllArticle

        readAllTopNews = repository.readAllTopNews
//        Log.d("NewsViewModel", "${readAllData.value!!.size}")

    }

    fun fetchAllNews(){
        val categories = listOf<String>("sports","business","entertainment","general","health","science","technology"/*, "top-US"*/)

        viewModelScope.launch {
            for(category in categories){
                _articles.value = NewsApi.retrofitService.getAllNews(category).articles
                Log.d("9999999999", "API fetch successful")
                try{
                    if(_articles.value!!.size > 0){
                        viewModelScope.launch(Dispatchers.IO) {
                            insertIntoDB(category, _articles)
                        }
                    }
                }catch (e: Exception){
                    Log.d("ALLLLLLL","$e     $articles")
                    _articles.value = listOf()
                }
            }

            _articles.value = NewsApi.retrofitService.getAllNews("").articles
            Log.d("777777777", "API TOP US NEWS fetch successful")
            try {
                if (_articles.value!!.size > 0) {
                    viewModelScope.launch(Dispatchers.IO) {
                        insertIntoDB("top-US", _articles)
                    }
                }
            }catch (e: Exception){
                Log.d("TOP US ALLLLLLL", "$e     $articles")
                _articles.value = listOf()
            }
        }
    }

    fun fetchTopNews(){
        if(MainActivity.checkConnectivity(this.getApplication<Application>().applicationContext)){
            viewModelScope.launch {
                _topNews.value = NewsApi.retrofitService.getAllNews("").articles
                try {
                    if(topNews.value!!.size > 0){
                        viewModelScope.launch(Dispatchers.IO) {
                            deleteArticleByCategory("top-US")
                            insertIntoDB("top-US", _topNews)
                        }
                    }
                }catch (e: Exception){
                    _topNews.value = listOf()
                }
            }
        }

    }


    fun addArticle(article: ArticleForRoomDB){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }

    fun deleteArticleByCategory(category: String){
        viewModelScope.launch {
            repository.deleteArticleByCategory(category)
        }
    }

    fun insertIntoDB( category: String, newsList: MutableLiveData<List<Article>>){
        if(category=="general"){
            Log.d("GENERAL NEWS KOITA?","GEN size = ${newsList.value?.size}")
        }
        for(news in newsList.value!!){
            val article = ArticleForRoomDB(
                0,
                news.author,
                news.content,
                news.description,
                news.publishedAt,
                news.title,
                news.url!!,
                news.urlToImage,
                category,
                false
            )
            addArticle(article)
        }
    }
    fun addBookmark(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.addBookmark(id)
        }
    }

    fun removeBookmark(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.removeBookmark(id)
        }
    }

    fun isBookmarked(id: Int): LiveData<Boolean>{
        return repository.isBookmarked(id)
    }

    private fun getAllNews(){

    }

    /*private fun getTopNews(){
        viewModelScope.launch{
            try {

                _topNews.value = NewsApi.retrofitService.getTopNews().articles
                Log.d("9999999999", "API fetch successful")

                if(topNews.value!!.size > 0){
                    viewModelScope.launch(Dispatchers.IO) {
                        insertIntoDB("top", _topNews)
                    }
                }

            }catch (e:Exception){
                Log.d("5555555555","$e     $articles")
                _topNews.value = listOf()
            }
        }
    }*/

    /*private fun getSportsNews(){
        viewModelScope.launch{
            try {
                _sportsNews.value = NewsApi.retrofitService.getSportsNews().articles

                if(sportsNews.value!!.size > 0){
                    viewModelScope.launch(Dispatchers.IO) {
                        insertIntoDB("sports", _sportsNews)
                    }
                }

            }catch (e:Exception){
                Log.d("000000000000000","$e     $articles")
                _topNews.value = listOf()
            }
        }
    }*/

//    fun getNews():LiveData<List<ArticleForRoomDB>> = repository.getAllNews()

    fun getNewsFromDB(category: String): LiveData<List<ArticleForRoomDB>> = repository.getNewsByCategory(category)

}