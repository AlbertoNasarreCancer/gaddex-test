package com.example.demo.controller

import com.example.demo.datasource.EventRepo
import com.example.demo.model.Event
import com.example.demo.service.EventService
import com.example.demo.service.EventServiceImage
import org.apache.commons.lang3.ObjectUtils
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/events")
class EventController(private val service: EventService,private val serviceImage: EventServiceImage, private val eventrepository : EventRepo) {


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

    @PostMapping(value= ["/image/{id}"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun setProfilePicture(@PathVariable("id") id: Int, @RequestParam file: MultipartFile): ResponseEntity<Void>{
        return try {
            serviceImage.setProfilePicture(id, file)
            ResponseEntity
                    .created(URI("/image/{id}"))
                    .build()
        } catch(error: NoSuchElementException){
            ResponseEntity
                    .notFound()
                    .build()
        }
    }

    @GetMapping("/image/{id}")
    fun getProfilePicture(@PathVariable("id") id: Int): ResponseEntity<Any>{

        return try {
            val image: ByteArray = serviceImage.getProfilePicture(id)

            ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
                    .body(image)

        } catch(error: NoSuchElementException){
            ResponseEntity
                    .notFound()
                    .build()
        }

    }

    @GetMapping("/events")
    fun fetchEvents(): ResponseEntity<List<Event>> {
        val events = eventrepository.findAll()
        if (events.isEmpty()) {
            return ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<List<Event>>(events, HttpStatus.OK)
    }

    @GetMapping("/events/{id}")
    fun fetchEventById(@PathVariable("id") id: Int): ResponseEntity<Event> {
        val gadget = eventrepository.findById(id)
        if (gadget.isPresent) {
            return ResponseEntity<Event>(gadget.get(), HttpStatus.OK)
        }
        return ResponseEntity<Event>(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/events")
    fun addNewEvent(@RequestBody event: Event, uri: UriComponentsBuilder): ResponseEntity<Event> {
        val persistedGadget = eventrepository.save(event)
        if (ObjectUtils.isEmpty(persistedGadget)) {
            return ResponseEntity<Event>(HttpStatus.BAD_REQUEST)
        }
        val headers = HttpHeaders()
        headers.setLocation(uri.path("/events/{id}").buildAndExpand(event.Id).toUri());
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @PutMapping("/events/{id}")
    fun updateEventtById(@PathVariable("id") id: Int, @RequestBody event: Event): ResponseEntity<Event> {
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

    @DeleteMapping("/events/{id}")
    fun removeEventById(@PathVariable("id") id: Int): ResponseEntity<Void> {
        val gadget = eventrepository.findById(id)
        if (gadget.isPresent) {
            eventrepository.deleteById(id)
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

}

