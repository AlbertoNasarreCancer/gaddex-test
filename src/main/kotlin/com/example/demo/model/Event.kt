package com.example.demo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

//@Entity
data class Event (

        val Id: Int,
        val Title: String,
        val Description: String,
        val Date: LocalDate,
        val Location: ArrayList<Double>

        )
/*
{

        @PrimaryKey(autoGenerate = true)
        val Id: Int = 0

}*/
