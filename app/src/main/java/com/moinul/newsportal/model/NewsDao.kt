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

    @Query("UPDATE article_table SET bookmarked='true' where id= :id")
    suspend fun addBookmark(id: Int)

    @Query("UPDATE article_table SET bookmarked='false' where id= :id")
    suspend fun removeBookmark(id: Int)

    @Query("SELECT bookmarked FROM article_table WHERE id= :id")
    fun isBookmarked(id: Int): LiveData<Boolean>
/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark_table")
    fun readAllBookmark(): LiveData<List<Bookmark>>

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)*/

    @Query("DELETE FROM article_table WHERE category= :category")
    suspend fun deleteArticleByCategory(category: String)




}