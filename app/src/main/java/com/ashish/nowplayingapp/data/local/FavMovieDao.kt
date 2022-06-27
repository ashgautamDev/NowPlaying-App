package com.ashish.nowplayingapp.data.local

import androidx.room.*
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavMovieDao {

    @Query("SELECT * FROM fav_movie")
    fun getAllFavouritesMovies() : Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovie(movie: Movie)

    @Delete
    suspend fun deleteFavMovie(movie: Movie)
}