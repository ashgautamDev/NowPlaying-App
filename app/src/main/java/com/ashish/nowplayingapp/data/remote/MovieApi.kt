package com.ashish.nowplayingapp.data.remote

import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.model.MovieResponse
import com.ashish.nowplayingapp.utils.ListState
import com.ashish.nowplayingapp.utils.Secrets.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber : Int
    ) : MovieResponse

 @GET("3/movie/{query}")
    suspend fun getMovies(
     @Path("query") listQuery : String = "popular",
     @Query("api_key") apiKey: String = API_KEY,
     @Query("page") pageNumber : Int
    ) : MovieResponse

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber : Int
    ) : MovieResponse

    @GET("3/movie/{id}?")
    suspend fun getMovieFromId(
        @Path("id") movieId : Long,
        @Query("api_key") apiKey: String = API_KEY
    ) : Movie

}