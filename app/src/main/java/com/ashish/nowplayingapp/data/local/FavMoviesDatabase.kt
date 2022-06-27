package com.ashish.nowplayingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie

@Database(entities = [Movie::class] ,  version = 2 , exportSchema = false)
abstract class FavMoviesDatabase : RoomDatabase(){
    abstract fun getFavMovieDao() : FavMovieDao
}