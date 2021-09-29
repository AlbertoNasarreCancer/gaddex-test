package com.example.demo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.tomcat.util.http.parser.MediaType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class EventControllerTest @Autowired constructor(

        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper

){
    val baseUrl = "http://localhost:8080/api/events"

    @Nested
    @DisplayName("GET /api/events")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetEvents{

        @Test
        fun `should return all Events`() {
            // when/then
            mockMvc.get(baseUrl)
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        // content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$[0].id") { value("1") }
                    }
        }
    }



    }





