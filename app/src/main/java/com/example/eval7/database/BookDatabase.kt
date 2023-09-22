package com.example.eval7.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Book::class],version=1)
abstract class BookDatabase : RoomDatabase() {
    abstract val bookDao : BookDao

    companion object{
        @Volatile
        private var INSTANCE : BookDatabase? = null

        fun getInstance(context: Context) : BookDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "booksDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}