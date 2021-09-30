package com.example.demo.model


import lombok.AllArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
class Event(var Title: String? = null, var Description: String? = null, @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var Id: Int = 1,var Date: LocalDate? = null,var Location: ArrayList<Double> = ArrayList<Double>(2),var Image: ByteArray=ByteArray(1)){


}
/*
{

        @PrimaryKey(autoGenerate = true)
        val Id: Int = 0

}*/
