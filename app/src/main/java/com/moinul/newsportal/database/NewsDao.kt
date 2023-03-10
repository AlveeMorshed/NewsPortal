package com.moinul.newsportal.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: ArticleForRoomDB)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("DELETE from bookmark_table where id= :id")
    suspend fun deleteBookmark(id: Int)
    @Query("SELECT * FROM article_table")
    fun readAllArticle(): LiveData<List<ArticleForRoomDB>>
    @Query("SELECT * FROM article_table WHERE category= :category")
    fun readArticleByCategory(category: String): LiveData<List<ArticleForRoomDB>>
    @Query("SELECT * FROM bookmark_table")
    fun readAllBookmark(): LiveData<List<Bookmark>>
    @Update
    suspend fun updateBookmark(article: ArticleForRoomDB)
    @Update
    suspend fun removeBookmark(article: ArticleForRoomDB)
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

    @Query("UPDATE article_table SET bookmarked=0 WHERE id=:id")
    suspend fun removeBookmark(id: Int)
}