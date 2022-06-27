package com.ashish.nowplayingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fav_movie")
data class Movie(
    @PrimaryKey
    val id: Long,
    val backdrop_path: String,
    val original_title: String,
    val popularity: Float,
    val vote_average: Float,
    val overview: String
){
    companion object{
        fun sampleMovie() = Movie(
            34435, "h31SOVlekuHXsMWVGxI8nPPfY82.jpg", "The Inception ", 54957F, 3.7f,"This Movie is outstanding"
        )
    }
}