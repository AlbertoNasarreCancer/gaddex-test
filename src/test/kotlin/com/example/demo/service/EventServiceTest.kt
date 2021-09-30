package com.example.demo.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class EventServiceTest{

    private val dataSource: EventDataSource = mockk(relaxed = true)

    private val eventService = EventService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // when
        eventService.getEvents()

        // then
        verify(exactly = 1) { dataSource.retrieveEvents() }
    }


}