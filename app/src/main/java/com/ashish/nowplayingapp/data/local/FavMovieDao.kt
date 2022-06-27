package com.ashish.nowplayingapp.data.local

import androidx.room.*
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavMovieDao {

    @Query("SELECT * FROM fav_movie")
    fun getAllFavouritesMovies() : Flow<List<Movie>>

    @Query("SELECT EXISTS (SELECT 1 FROM fav_movie WHERE id = :id)")
    suspend fun isMovieFav(id : Long) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovie(movie: Movie)

    @Delete
    suspend fun deleteFavMovie(movie: Movie)
}