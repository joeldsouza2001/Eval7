package com.example.eval7.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class  Book(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title : String,
    val author : String,
    var doneReading : Boolean
)
