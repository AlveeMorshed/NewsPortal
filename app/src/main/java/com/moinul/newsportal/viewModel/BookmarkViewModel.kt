package com.moinul.newsportal.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.moinul.newsportal.model.ArticleForRoomDB
import com.moinul.newsportal.model.Bookmark
import com.moinul.newsportal.model.NewsDatabase
import com.moinul.newsportal.repository.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Bookmark>>
    val repository: BookmarkRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).getDao()
        repository = BookmarkRepository(newsDao)
        readAllData = repository.readAllBookmark

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
    fun getBookmarkFromDB(): LiveData<List<Bookmark>> = repository.readAllBookmark

}