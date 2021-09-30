package com.example.demo.datasource


import androidx.room.Database
import com.example.demo.model.Event

@Database(entities = arrayOf(Event::class),version = 1)
abstract class EventDatabase  {
    abstract fun userDao(): EventDao
}