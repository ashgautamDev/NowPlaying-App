package com.ashish.nowplayingapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashish.nowplayingapp.data.repository.MovieRepository
import com.ashish.nowplayingapp.model.Movie

class MoviePagingSource(
    private val movieRepository: MovieRepository,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1

            val movieResponse = movieRepository.getMovies(nextPage, query = query)

            LoadResult.Page(
                data = movieResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieResponse.page.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return 1
    }


}