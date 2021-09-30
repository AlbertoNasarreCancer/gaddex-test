package com.example.demo.service

import com.example.demo.datasource.EventDataSource
import com.example.demo.datasource.EventRepo
import com.example.demo.model.Event
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EventService(private val datasource: EventDataSource) {

    fun getEvents(): Collection<Event> = datasource.retrieveEvents()
    fun getEvent(Id: Int): Event = datasource.retrieveEvent(Id)
    fun addEvent(event: Event): Event = datasource.CreateEvent(event)
    fun updateEvent(event: Event): Event = datasource.updateEvent(event)
    fun deleteEvent(id: Int): Unit = datasource.deleteBank(id)
}

