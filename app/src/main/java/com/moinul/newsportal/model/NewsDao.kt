package com.moinul.newsportal.model

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: ArticleForRoomDB)

    @Query("SELECT * FROM article_table")
    fun readAllArticle(): LiveData<List<ArticleForRoomDB>>
    @Query("SELECT * FROM article_table WHERE category= :category")
    fun readArticleByCategory(category: String): LiveData<List<ArticleForRoomDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark_table")
    fun readAllBookmark(): LiveData<List<Bookmark>>

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)




}