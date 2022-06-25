package com.ashish.nowplayingapp.data.repository

import com.ashish.nowplayingapp.data.remote.MovieApi

import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi){

    suspend fun getNowPlayingMovies(pageNumber : Int) =
        movieApi.getNowPlayingMovies(pageNumber = pageNumber)

    suspend fun getPopularMovies(pageNumber : Int) =
        movieApi.getPopularMovies(pageNumber = pageNumber)

    suspend fun getMovie(id : Long) =
        movieApi.getMovies(movieId = id)

}