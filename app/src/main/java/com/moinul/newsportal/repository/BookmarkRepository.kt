package com.moinul.newsportal.repository

import androidx.lifecycle.LiveData
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.model.NewsDao

class BookmarkRepository(private val newsDao: NewsDao) {
    val readAllBookmark: LiveData<List<Bookmark>> = newsDao.readAllBookmark()
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