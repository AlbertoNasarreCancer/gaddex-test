package com.example.demo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Event (

        @PrimaryKey(autoGenerate = true) val Id: Int,
        @ColumnInfo val Title: String,
        @ColumnInfo val Description: String,
        @ColumnInfo val Date: LocalDate,
        @ColumnInfo val Location: ArrayList<Double>,
        // val Image: MultipartFile

        )
/*
{

        @PrimaryKey(autoGenerate = true)
        val Id: Int = 0

}*/
