package com.ashish.nowplayingapp.data.repository

import com.ashish.nowplayingapp.data.local.FavMovieDao
import com.ashish.nowplayingapp.model.FavMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavMovieRepository @Inject constructor(private val favMovieDao: FavMovieDao) {

    fun getAllFavouritesMovies() : Flow<List<FavMovie>> =
        favMovieDao.getAllFavouritesMovies().flowOn(Dispatchers.IO).conflate()

    suspend fun insertFavMovie(movie: FavMovie) = favMovieDao.insertFavMovie(movie)

    suspend fun deleteFavMovie(movie: FavMovie) = favMovieDao.deleteFavMovie(movie)
}
