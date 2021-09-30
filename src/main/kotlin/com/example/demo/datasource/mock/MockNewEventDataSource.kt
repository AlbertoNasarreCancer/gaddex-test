package com.example.demo.datasource.mock

import com.example.demo.datasource.EventRepo
import com.example.demo.datasource.NewEventDataSource
import com.example.demo.model.Event
import org.springframework.stereotype.Repository

import com.example.demo.datasource.*

@Repository
class MockNewEventDataSource : NewEventDataSource {


    override fun retrieveEvents(): Collection<Event> {
        TODO("Not yet implemented")
    }

    override fun retrieveEvent(id: Int): Event {
        TODO("Not yet implemented")
    }

    override fun CreateEvent(event: Event): Event {
        TODO("Not yet implemented")
    }

    override fun updateEvent(event: Event): Event {
        TODO("Not yet implemented")
    }

    override fun deleteBank(id: Int) {
        TODO("Not yet implemented")
    }

}