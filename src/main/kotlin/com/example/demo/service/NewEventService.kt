package com.example.demo.service

import com.example.demo.datasource.EventRepo
import com.example.demo.datasource.NewEventDataSource
import com.example.demo.model.Event
import org.apache.commons.lang3.ObjectUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class NewEventService(private val datasource: NewEventDataSource, private val eventrepository : EventRepo) {

    fun getEvents(): ResponseEntity<List<Event>> {
        val events = eventrepository.findAll()
        if (events.isEmpty()) {
            return ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<Event>>(events, HttpStatus.OK)
    }
    fun getEvent(Id: Int): ResponseEntity<Event> {

            val gadget = eventrepository.findById(Id)
            if (gadget.isPresent) {
                return ResponseEntity<Event>(gadget.get(), HttpStatus.OK)
            }
            return ResponseEntity<Event>(HttpStatus.NOT_FOUND)
        }


    fun addEvent(event: Event, uri: UriComponentsBuilder): ResponseEntity<Event> {

        val persistedGadget = eventrepository.save(event)
        if (ObjectUtils.isEmpty(persistedGadget)) {
            return ResponseEntity<Event>(HttpStatus.BAD_REQUEST)
        }
        val headers = HttpHeaders()
        headers.setLocation(uri.path("/events/{id}").buildAndExpand(event.Id).toUri());
        return ResponseEntity(headers, HttpStatus.CREATED)


    }
    fun updateEvent(event: Event,id: Int): ResponseEntity<Event> {

        return eventrepository.findById(id).map {
            eventDetails ->
            val updatedEvent: Event = eventDetails.copy(
                    Title = event.Title,
                    Date = event.Date,
                    Location = event.Location,
                    Image = event.Image,
                    Description = event.Description
            )
            ResponseEntity(eventrepository.save(updatedEvent), HttpStatus.OK)
        }.orElse(ResponseEntity<Event>(HttpStatus.INTERNAL_SERVER_ERROR))


    }
    fun deleteEvent(id: Int): ResponseEntity<Void> {
        val gadget = eventrepository.findById(id)
        if (gadget.isPresent) {
            eventrepository.deleteById(id)
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
    }


}