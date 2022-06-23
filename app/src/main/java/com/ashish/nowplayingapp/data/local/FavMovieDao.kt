package com.ashish.nowplayingapp.data.local

import androidx.room.*
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavMovieDao {

    @Query("SELECT * FROM fav_movies")
    fun getAllFavouritesMovies() : Flow<List<FavMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMovie(movie: FavMovie)

    @Delete
    suspend fun deleteFavMovie(movie: FavMovie)
}