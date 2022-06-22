package com.ashish.nowplayingapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashish.nowplayingapp.data.repository.MovieRepository
import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.model.MovieResponse
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PagingSource<Int , Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val nowPlayingMovieResponse = movieRepository.getNowPlayingMovies(nextPage)
            val popularMovieResponse = movieRepository.getPopularMovies(nextPage)
            LoadResult.Page(
                data = nowPlayingMovieResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nowPlayingMovieResponse.page.plus(1)
            )
//            LoadResult.Page(
//                data = popularMovieResponse.results,
//                prevKey = if (nextPage == 1) null else nextPage - 1,
//                nextKey = popularMovieResponse.page.plus(1)
//            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}