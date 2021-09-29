package com.example.demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

//@Entity
data class Event (

        val Id: Int,
        val Title: String,
        val Description: String,
        val Date: LocalDate,
        val Location: ArrayList<Double>,
        // val Image: MultipartFile

        )
/*
{

        @PrimaryKey(autoGenerate = true)
        val Id: Int = 0

}*/
