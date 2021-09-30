package com.example.demo.model


import lombok.AllArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table
@AllArgsConstructor
data class Event (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val Id: Int,
        @Column
        val Title: String,
        @Column
        val Description: String,
        @Column
        val Date: LocalDate,
        @Column
        val Location: ArrayList<Double>,
        @Lob
        @Column
        var Image: ByteArray

        )
/*
{

        @PrimaryKey(autoGenerate = true)
        val Id: Int = 0

}*/
