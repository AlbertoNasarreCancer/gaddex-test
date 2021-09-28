package com.example.demo.datasource.mock

import com.example.demo.datasource.EventDataSource
import com.example.demo.model.Event
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockEventDataSource : EventDataSource {

    val events = mutableListOf(
            Event(Id = 1,Title = "test",Description = "descriptivism1",Date="01-01-2019",Location=1.0),
            Event(Id = 2,Title = "test2",Description = "descriptivism2",Date="01-01-2020",Location=2.0)
    )

    override fun retrieveEvents(): Collection<Event> = events
    override fun retrieveEvent(id: Int): Event {
        return events.firstOrNull { it.Id == id }
                ?:throw NoSuchElementException("Could not find a back with event with id: $id")
    }

    override fun CreateEvent(event: Event): Event {
        if (events.any{ it.Id == event.Id}){
            throw IllegalArgumentException("bank with Id ${event.Id} already exist")
        }

        events.add(event)

        return event
    }

    override fun updateEvent(event: Event): Event {
        val currentEvent = events.firstOrNull() { it.Id == event.Id }
                ?: throw NoSuchElementException("Could not find a bank with account number ${event.Id}")

        events.remove(currentEvent)
        events.add(event)

        return currentEvent
    }

    override fun deleteBank(id: Int) {
        val currentEvent = events.firstOrNull() { it.Id == id }
                ?: throw NoSuchElementException("Could not find a bank with account number $id")

        events.remove(currentEvent)
    }


}