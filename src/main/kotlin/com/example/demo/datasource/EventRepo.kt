package com.example.demo.datasource

import com.example.demo.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepo : JpaRepository<Event, Long> {
}