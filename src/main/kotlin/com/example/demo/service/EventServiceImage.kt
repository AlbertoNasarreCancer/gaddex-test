package com.example.demo.service

import com.example.demo.datasource.EventRepo
import com.example.demo.model.Event
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EventServiceImage(private val eventRepo: EventRepo) {

    fun setProfilePicture(id: Long, file: MultipartFile){
        val event : Event = eventRepo.findById(id).orElseThrow()

        eventRepo.save(event)
    }



}