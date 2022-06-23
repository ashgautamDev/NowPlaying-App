package com.ashish.nowplayingapp.model


data class Movie(
    val id : Long,
    val backdrop_path: String,
    val original_title: String,
    val popularity: Long,
    val vote_average : Float
){
    companion object{
        fun sampleMovie() = Movie(
            34435,"h31SOVlekuHXsMWVGxI8nPPfY82.jpg","The Inception " , 54957 , 3.7f
        )
    }
}