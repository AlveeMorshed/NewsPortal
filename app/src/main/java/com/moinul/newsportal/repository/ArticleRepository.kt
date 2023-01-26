package com.moinul.newsportal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark

import com.moinul.newsportal.database.NewsDao

class ArticleRepository(private val newsDao: NewsDao) {
    val readAllArticle: LiveData<List<ArticleForRoomDB>> = newsDao.readAllArticle()
    val readAllBookmark: LiveData<List<Bookmark>> = newsDao.readAllBookmark()

    val readAllTopNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("top-US")
    val readAllBusinessNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("business")
    val readAllEntertainmentNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("entertainment")
    val readAllGeneralNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("general")
    val readAllHealthNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("health")
    val readAllScienceNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("science")
    val readAllSportsNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("sports")
    val readAllTechnologyNews: LiveData<List<ArticleForRoomDB>> = newsDao.readArticleByCategory("technology")
    suspend fun addArticle(article: ArticleForRoomDB){

        newsDao.addArticle(article)
    }

    fun readAllTopNews():LiveData<List<ArticleForRoomDB>>{
        return newsDao.readArticleByCategory("top-US")
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
        newsDao.addBookmark(bookmark)
    }
    suspend fun deleteBookmark(id: Int){
        newsDao.removeBookmark(id)
        newsDao.deleteBookmark(id)
    }

    fun getBookmarkFromDB():LiveData<List<Bookmark>>{
        return newsDao.readAllBookmark()
    }



}