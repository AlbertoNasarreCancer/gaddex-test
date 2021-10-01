package com.example.demo.datasource

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.IllegalArgumentException

@DataJpaTest
internal class datasourceTest(@Autowired private val eventrepository : EventRepo) {


    @Test
    fun `should provide some mock data`() {
        // when
        val events = eventrepository.findAll()

        // then
        //assertThat(events).allMatch { it.Description?.isNotBlank() ?: throw IllegalArgumentException }
        assertThat(events).allMatch { it.Id != 0 }
    }

}