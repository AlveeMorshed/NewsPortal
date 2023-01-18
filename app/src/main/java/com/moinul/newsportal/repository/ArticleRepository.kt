package com.moinul.newsportal.repository

import androidx.lifecycle.LiveData
import com.moinul.newsportal.model.Article
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.model.NewsDao

class ArticleRepository(private val newsDao: NewsDao) {
    val readAllArticle: LiveData<List<Article>> = newsDao.readAllArticle()
    suspend fun addArticle(article: Article){
        newsDao.addArticle(article)
    }

}