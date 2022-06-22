package com.ashish.nowplayingapp.data.remote

import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "YOUR_API_KEY_HERE",
        @Query("page") pageNumber : Int
    ) : MovieResponse

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") pageNumber : Int
    ) : MovieResponse

    @GET("3/movie/{id}")
    suspend fun getMovies(
        @Path("id") movieId : Int
    ) : Movie

}