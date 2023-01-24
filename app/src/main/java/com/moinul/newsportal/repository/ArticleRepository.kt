package com.moinul.newsportal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark

import com.moinul.newsportal.model.NewsDao

class ArticleRepository(private val newsDao: NewsDao) {
    val readAllArticle: LiveData<List<ArticleForRoomDB>> = newsDao.readAllArticle()

    val readAllTopNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("top-US")
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

    suspend fun updateBookmark(article: ArticleForRoomDB){
        newsDao.deleteBookmark(article.id)
        newsDao.updateBookmark(article)
    }


    fun isBookmarked(id: Int): LiveData<Boolean>{
        return newsDao.isBookmarked(id)
    }

    suspend fun deleteArticleByCategory(category: String){
        return newsDao.deleteArticleByCategory(category)
    }


    suspend fun addBookmark(bookmark: Bookmark){
        return newsDao.addBookmark(bookmark)
    }

    suspend fun deleteBookmark(id: Int){
        return newsDao.deleteBookmark(id)
    }

}