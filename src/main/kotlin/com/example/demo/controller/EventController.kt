package com.example.demo.controller

import com.example.demo.datasource.EventRepo
import com.example.demo.model.Event
import com.example.demo.service.EventServiceImage
import com.example.demo.service.NewEventService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/api/events")
class EventController(private val serviceImage: EventServiceImage, private val eventrepository : EventRepo, private val newservice: NewEventService) {


    @GetMapping("/events")
    fun fetchEvents(): ResponseEntity<List<Event>> = newservice.getEvents()

    @GetMapping("/events/{id}")
    fun fetchEventById(@PathVariable("id") id: Int): ResponseEntity<Event> = newservice.getEvent(id)

    @PostMapping("/events")
    fun addNewEvent(@RequestBody event: Event, uri: UriComponentsBuilder): ResponseEntity<Event> = newservice.addEvent(event,uri)

    @PutMapping("/events/{id}")
    fun updateEventtById(@PathVariable("id") id: Int, @RequestBody event: Event): ResponseEntity<Event> = newservice.updateEvent(event,id)
    @DeleteMapping("/events/{id}")
    fun removeEventById(@PathVariable("id") id: Int): ResponseEntity<Void> = newservice.deleteEvent(id)

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



}

