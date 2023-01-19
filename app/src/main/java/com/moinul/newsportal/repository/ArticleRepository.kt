package com.moinul.newsportal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.model.NewsDao

class ArticleRepository(private val newsDao: NewsDao) {
    val readAllArticle: LiveData<List<ArticleForRoomDB>> = newsDao.readAllArticle()
    suspend fun addArticle(article: ArticleForRoomDB){

        newsDao.addArticle(article)
    }

    fun getNewsByCategory(category: String): LiveData<List<ArticleForRoomDB>>{
        return newsDao.readArticleByCategory(category)
    }

    fun getAllNews():LiveData<List<ArticleForRoomDB>>{
        Log.d("GETTING ALL NEWS", "YESSSSSSSSSSSSSS")
        return newsDao.readAllArticle()
    }

}