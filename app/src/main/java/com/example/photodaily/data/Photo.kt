package com.example.photodaily.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    var photo: String,
    var date: String,
    var subject:String
    ){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
