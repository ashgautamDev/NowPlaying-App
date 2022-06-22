package com.ashish.nowplayingapp.model


data class Movie(
    val id : Long,
    val backdrop_path: String,
    val original_title: String,
    val popularity: Long,
    val vote_average : Float
)