package com.moinul.newsportal.repository

import androidx.lifecycle.LiveData
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.model.NewsDao

class BookmarkRepository(private val newsDao: NewsDao) {
    val readAllBookmark: LiveData<List<Bookmark>> = newsDao.readAllBookmark()
    suspend fun addBookmark(bookmark: Bookmark){
        newsDao.addBookmark(bookmark)
    }
    suspend fun deleteBookmark(bookmark: Bookmark){
        newsDao.deleteBookmark(bookmark)
    }
}