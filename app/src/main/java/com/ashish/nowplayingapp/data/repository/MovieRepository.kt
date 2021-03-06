package com.ashish.nowplayingapp.data.repository

import com.ashish.nowplayingapp.data.remote.MovieApi

import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {


    suspend fun getMovies(pageNumber: Int, query: String) =
        movieApi.getMovies(pageNumber = pageNumber, listQuery = query)


    suspend fun getMovie(id: Long) =
        movieApi.getMovieFromId(movieId = id)

}