package com.example.demo.controller

import com.example.demo.model.Event
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.awt.Image
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
internal class EventControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper
) {

    val baseUrl = "http://localhost:8080/api/events/"

    @Nested
    @DisplayName("GET /api/events/")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetEvents {

        @Test
        fun `should return all events`() {
            // when/then
            mockMvc.get(baseUrl)
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        //content { contentType(MediaType.APPLICATION_JSON) }
                        //jsonPath("$[0].account_number") { value("1234") }
                    }
        }
    }

    @Nested
    @DisplayName("GET /api/events/{id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetEvent {

        @Test
        fun `should return the event with the given id`() {
            // given
            val id = 1

            // when/then
            mockMvc.get("$baseUrl/$id")
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                    }
        }

        @Test
        fun `should return NOT FOUND if the account number does not exist`() {
            // given
            val idNumber = "does_not_exist"

            // when/then
            mockMvc.get("$baseUrl/$idNumber")
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/events")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewEvent {

        @Test
        fun `should add the new event`() {
            // given
            val newEvent= Event(Title = "hello","descriptivism1",Date= LocalDate.parse("2018-12-12"),Location=ArrayList<Double>(2),Image=ByteArray(10))

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newEvent)
            }

            // then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content {
                            contentType(MediaType.APPLICATION_JSON)
                            json(objectMapper.writeValueAsString(newEvent))
                        }
                    }

            mockMvc.get("$baseUrl/${newEvent.Id}")
                    .andExpect { content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newEvent)) }
                    }
        }

        @Test
        fun `should return BAD REQUEST if event with given account number already exists`() {
            // given
            val invalidEvent = Event(Description = "descriptivism1",Date= LocalDate.parse("2018-12-12"),Location=ArrayList<Double>(2),Image=ByteArray(10))

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidEvent)
            }

            // then
            performPost
                    .andDo { print() }
                    .andExpect { status { isBadRequest() } }
        }
    }




}