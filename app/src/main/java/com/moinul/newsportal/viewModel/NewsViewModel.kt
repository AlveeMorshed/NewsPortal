package com.moinul.newsportal.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.moinul.newsportal.MainActivity
import com.moinul.newsportal.database.NewsDatabase
import com.moinul.newsportal.model.*
import com.moinul.newsportal.network.NewsApi
import com.moinul.newsportal.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): AndroidViewModel(application) {
    val repository: ArticleRepository

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _topNews = MutableLiveData<List<Article>>()
    val topNews: LiveData<List<Article>> = _topNews

    private val _sportsNews = MutableLiveData<List<Article>>()
    val sportsNews: LiveData<List<Article>> = _sportsNews

    private val _businessNews = MutableLiveData<List<Article>>()
    val businessNews: LiveData<List<Article>> = _businessNews

    private val _entertainmentNews = MutableLiveData<List<Article>>()
    val entertainmentNews: LiveData<List<Article>> = _entertainmentNews

    private val _generalNews = MutableLiveData<List<Article>>()
    val generalNews: LiveData<List<Article>> = _generalNews

    private val _healthNews = MutableLiveData<List<Article>>()
    val healthNews: LiveData<List<Article>> = _healthNews

    private val _scienceNews = MutableLiveData<List<Article>>()
    val scienceNews: LiveData<List<Article>> = _scienceNews

    private val _technologyNews = MutableLiveData<List<Article>>()
    val technologyNews: LiveData<List<Article>> = _technologyNews



    var readAllNewsData: LiveData<List<ArticleForRoomDB>>
    var readAllBookmarkData: LiveData<List<Bookmark>>

    var readAllTopNews : LiveData<List<ArticleForRoomDB>>
    var readAllBusinessNews : LiveData<List<ArticleForRoomDB>>
    var readAllEntertainmentNews : LiveData<List<ArticleForRoomDB>>
    var readAllGeneralNews: LiveData<List<ArticleForRoomDB>>
    var readAllHealthNews : LiveData<List<ArticleForRoomDB>>
    var readAllSportsNews : LiveData<List<ArticleForRoomDB>>
    var readAllScienceNews : LiveData<List<ArticleForRoomDB>>
    var readAllTechnologyNews : LiveData<List<ArticleForRoomDB>>

    init {
        val newsDao = NewsDatabase.getDatabase(application).getDao()
        repository = ArticleRepository(newsDao)


        readAllNewsData = repository.readAllArticle
        readAllBookmarkData = repository.readAllBookmark

        readAllTopNews = repository.readAllTopNews
        readAllBusinessNews = repository.readAllBusinessNews
        readAllEntertainmentNews = repository.readAllEntertainmentNews
        readAllGeneralNews = repository.readAllGeneralNews
        readAllHealthNews = repository.readAllHealthNews
        readAllScienceNews = repository.readAllScienceNews
        readAllSportsNews = repository.readAllSportsNews
        readAllTechnologyNews = repository.readAllTechnologyNews


//        Log.d("NewsViewModel", "${readAllData.value!!.size}")

    }

    fun getFromDB(): LiveData<List<ArticleForRoomDB>>{
        return repository.readAllTopNews()
    }

    fun fetchAllNews(){
        val categories = listOf<String>("sports","business","entertainment","general","health","science","technology", "top-US")

        for(category in categories){
            fetchNewsByCategory(category)
        }

        /*GlobalScope.launch {
            for(category in categories){
                _articles.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                Log.d("9999999999", "API fetch successful")
                try{
                    if(_articles.value!!.size > 0){
                        viewModelScope.launch(Dispatchers.IO) {
                            deleteArticleByCategory(category)
                            insertIntoDB(category, _articles)
                        }
                    }
                }catch (e: Exception){
                    Log.d("ALLLLLLL","$e     $articles")
                    _articles.postValue(listOf())
                }
            }

            _articles.postValue(NewsApi.retrofitService.getAllNews("").articles)
            Log.d("777777777", "API TOP US NEWS fetch successful")
            try {
                if (_articles.value!!.size > 0) {
                    viewModelScope.launch(Dispatchers.IO) {
                        deleteArticleByCategory("top-US")
                        insertIntoDB("top-US", _articles)
                    }
                }
            }catch (e: Exception){
                Log.d("TOP US ALLLLLLL", "$e     $articles")
                _articles.postValue(listOf())
            }
        }*/
    }


    fun fetchNewsByCategory(category: String){
        if(MainActivity.checkConnectivity(this.getApplication<Application>().applicationContext)){
            GlobalScope.launch {
                when(category){
                    "top-US" -> {
                        _topNews.postValue(NewsApi.retrofitService.getAllNews("").articles)
                        try {
                            if(topNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _topNews)
                                }
                            }
                        }catch (e: Exception){
                            _topNews.postValue(listOf())

                        }
                    }
                    "business" -> {
                        _businessNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if(businessNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _businessNews)
                                }
                            }
                        }catch (e: Exception){
                            _businessNews.postValue(listOf())
                        }
                    }

                    "entertainment" -> {
                        _entertainmentNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if(entertainmentNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _entertainmentNews)
                                }
                            }
                        }catch (e: Exception){
                            _entertainmentNews.postValue(listOf())
                        }
                    }

                    "general" -> {
                        _generalNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if(generalNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category,_generalNews)
                                }
                            }
                        }catch (e: Exception){
                            _generalNews.postValue(listOf())
                        }
                    }

                    "health" -> {
                        _healthNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if(healthNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _healthNews)
                                }
                            }
                        }catch (e: Exception){
                            _healthNews.postValue(listOf())
                        }
                    }

                    "science" -> {
                        _scienceNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if (scienceNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _scienceNews)
                                }
                            }
                        }catch (e: Exception){
                            _scienceNews.postValue(listOf())
                        }

                    }
                    "sports" -> {
                        _sportsNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if(sportsNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _sportsNews)
                                }
                            }
                        }catch (e: Exception){
                            _sportsNews.postValue(listOf())
                        }
                    }
                    "technology" -> {
                        _technologyNews.postValue(NewsApi.retrofitService.getAllNews(category).articles)
                        try {
                            if(technologyNews.value!!.size > 0){
                                viewModelScope.launch(Dispatchers.IO) {
                                    deleteArticleByCategory(category)
                                    insertIntoDB(category, _technologyNews)
                                }
                            }
                        }catch (e: Exception){
                            _technologyNews.postValue(listOf())
                        }
                    }

                }

            }

        }else{
            Toast.makeText(MainActivity.appContext, "PLEASE TURN ON INTERNET", Toast.LENGTH_SHORT).show()
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
    fun updateBookmark(article: ArticleForRoomDB){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateBookmark(article)
        }
    }



    fun isBookmarked(id: Int): LiveData<Boolean>{
        return repository.isBookmarked(id)
    }

    fun addBookmark(bookmark: Bookmark){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookmark(bookmark)
        }
    }

    fun deleteBookmark(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmark(id)
        }
    }


    fun getNewsFromDB(category: String): LiveData<List<ArticleForRoomDB>> = repository.getNewsByCategory(category)

}