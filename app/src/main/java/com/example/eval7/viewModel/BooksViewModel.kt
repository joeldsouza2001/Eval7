package com.example.eval7.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.eval7.repo.BooksRepository
import com.example.eval7.database.Book
import com.example.eval7.database.BookDatabase
import kotlinx.coroutines.launch

class BooksViewModel(private val application: Application) : ViewModel() {

    private val bookRepo = BooksRepository(BookDatabase.getInstance(application))

    val allBooks: LiveData<List<Book>> = bookRepo.fetchAll()
    val unreadBooks: LiveData<List<Book>> = bookRepo.fetchUnread()


    fun insert(
        title: String,
        author: String,
        isRead: Boolean
    ) {
        if (title.isNotEmpty() && author.isNotEmpty()) {
            viewModelScope.launch {
                bookRepo.insertBook(Book(0, title, author, isRead))

            }
        }
    }

    fun update(book: Book) {
        viewModelScope.launch {
            bookRepo.updateBook(book)
        }
    }

    fun delete(book: Book) {
        viewModelScope.launch {
            bookRepo.deleteBook(book)
        }
    }


}


class BooksViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}