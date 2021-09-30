package com.example.demo.controller

import com.example.demo.model.Event
import com.example.demo.service.EventService
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventController(private val service: EventService) {


    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotfound(e: NoSuchElementException): ResponseEntity<String> =
            ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getEvents(): Collection<Event> = service.getEvents()

    @GetMapping("/{id}")
    fun getEvent(@PathVariable id: Int) = service.getEvent(id)

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    fun addEvent(@RequestBody event: Event) : Event = service.addEvent(event)

    @PatchMapping
    fun updateEvent(@RequestBody event: Event) : Event = service.updateEvent(event)

    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: Int): Unit = service.deleteEvent(id)
}

