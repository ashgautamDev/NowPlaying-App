package com.ashish.nowplayingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashish.nowplayingapp.model.FavMovie

@Database(entities = [FavMovie::class] ,  version = 1 , exportSchema = false)
abstract class FavMoviesDatabase : RoomDatabase(){
    abstract fun getFavMovieDao() : FavMovieDao
}