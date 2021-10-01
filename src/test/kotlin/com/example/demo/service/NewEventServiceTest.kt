package com.example.demo.service

import com.example.demo.datasource.EventRepo
import io.mockk.verify
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional


@DataJpaTest
internal class NewEventServiceTest( ){


    private val eventrepository : EventRepo = mockk(relaxed = true)
    private val EventService = NewEventService(eventrepository)

    @Test
    fun `should call its Repo to retrieve events`() {
        // when
        EventService.getEvents()

        // then
        verify(exactly = 1) { eventrepository.findAll() }
    }


}