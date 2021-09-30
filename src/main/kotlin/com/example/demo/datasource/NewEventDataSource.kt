package com.example.demo.datasource

import com.example.demo.model.Event

interface NewEventDataSource {

    fun retrieveEvents(): Collection<Event>
    fun retrieveEvent(id: Int): Event
    fun CreateEvent(event: Event): Event
    fun updateEvent(event: Event): Event
    fun deleteBank(id: Int)
}