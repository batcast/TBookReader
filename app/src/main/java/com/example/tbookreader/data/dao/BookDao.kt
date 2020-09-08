package com.example.tbookreader.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import nl.siegmann.epublib.domain.Book

@Dao
interface BookDao {

    fun observeAll():LiveData<List<Book>>
}