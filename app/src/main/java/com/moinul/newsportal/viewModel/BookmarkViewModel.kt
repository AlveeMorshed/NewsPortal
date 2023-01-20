package com.moinul.newsportal.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    /*val readAllData: LiveData<List<Bookmark>>
    val repository: BookmarkRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).getDao()
        repository = BookmarkRepository( newsDao)
        readAllData = repository.readAllBookmark

    }

    fun addBookmark(bookmark: Bookmark){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookmark(bookmark)
        }
    }
    fun deleteBookmark(bookmark: Bookmark){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmark(bookmark)
        }
    }*/

}