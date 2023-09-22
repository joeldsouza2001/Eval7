package com.example.eval7.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    fun insert(book:Book)

    @Delete
    fun delete(book: Book)

    @Query("select * from book")
    fun fetchAll(): LiveData<List<Book>>

    @Query("select * from book where doneReading=0")
    fun fetchUnread(): LiveData<List<Book>>

    @Update
    fun update(book: Book)
}