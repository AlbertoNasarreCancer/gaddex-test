package com.example.demo.datasource

import androidx.room.Dao
import androidx.room.Query
import com.example.demo.model.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM Event")
    fun getAll():List<Event>
}