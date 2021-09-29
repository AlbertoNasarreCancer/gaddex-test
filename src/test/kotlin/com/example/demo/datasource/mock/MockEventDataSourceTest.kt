package com.example.demo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockEventDataSourceTest{

    private val mockDataSource = MockEventDataSource()

    @Test
    fun `should provide a collection of banks`() {
        // when
        val events = mockDataSource.retrieveEvents()

        // then
        assertThat(events.size).isGreaterThanOrEqualTo(1)
    }

    @Test
    fun `should provide some mock data`() {
        // when
        val banks = mockDataSource.retrieveEvents()

        // then
        assertThat(banks).allMatch { it.Description.isNotBlank() }
        assertThat(banks).anyMatch { it.Id != 0 }
    }

}