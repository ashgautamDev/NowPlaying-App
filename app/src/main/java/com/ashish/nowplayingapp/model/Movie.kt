package com.ashish.nowplayingapp.model


data class Movie(
    val id : Long,
    val backdrop_path: String,
    val original_title: String,
    val popularity: Float,
    val vote_average : Float
){
    companion object{
        fun sampleMovie() = Movie(
            34435,"h31SOVlekuHXsMWVGxI8nPPfY82.jpg","The Inception " , 54957F , 3.7f
        )
    }
}