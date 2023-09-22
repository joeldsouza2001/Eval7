package com.example.eval7.repo

import android.media.audiofx.BassBoost
import androidx.lifecycle.LiveData
import com.example.eval7.database.Book
import com.example.eval7.database.BookDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BooksRepository(private val database: BookDatabase) {

    fun fetchAll(): LiveData<List<Book>> = database.bookDao.fetchAll()
    fun fetchUnread(): LiveData<List<Book>> = database.bookDao.fetchUnread()


    suspend fun insertBook(book: Book){
        withContext(Dispatchers.IO) {
            database.bookDao.insert(book)
        }

    }

    suspend fun updateBook(book: Book){
        withContext(Dispatchers.IO){
            database.bookDao.update(book)
        }
    }

    suspend fun deleteBook(book: Book){
        withContext(Dispatchers.IO){
            database.bookDao.delete(book)
        }
    }
}