package com.ashish.nowplayingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movies")
data class FavMovie(
    @PrimaryKey
    val id : Int
)
