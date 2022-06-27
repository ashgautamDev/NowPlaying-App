package com.ashish.nowplayingapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashish.nowplayingapp.data.paging.MoviePagingSource
import com.ashish.nowplayingapp.data.repository.FavMovieRepository
import com.ashish.nowplayingapp.data.repository.MovieRepository
import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.utils.FavViewState
import com.ashish.nowplayingapp.utils.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favMovieRepository: FavMovieRepository
) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _favState = MutableStateFlow<FavViewState>(FavViewState.Loading)

    // UI collects from this StateFlow to get it"s state update
    val favState = _favState.asStateFlow()

    val query = mutableStateOf(
        ListState.ALL_PLAYING.string
    )

    fun isMovieFav(id: Long) = viewModelScope.launch {
        favMovieRepository.isMovieFav(id)
    }

    val moviesData: Flow<PagingData<Movie>> =
        Pager(PagingConfig(pageSize = 10)) {
            MoviePagingSource(movieRepository, query.value)
        }.flow


    // get all movies id from Room Database
    init {
        viewModelScope.launch {
            favMovieRepository.getAllFavouritesMovies().collect() { result ->
                if (result.isNullOrEmpty()) {
                    _favState.value = FavViewState.Empty
                } else {
                    _favState.value = FavViewState.Success(result)

                }
            }
        }


    }


    // add to favourites by taking id
    fun addFavMovie(movie: Movie) = viewModelScope.launch {
        favMovieRepository.insertFavMovie(movie)
    }

    // deleting from db
    fun deleteFavMovie(movie: Movie) = viewModelScope.launch {
        favMovieRepository.deleteFavMovie(movie)
    }

    fun getMovieFromId(movieId: Long) = viewModelScope.launch {
        movieRepository.getMovie(movieId)
    }


}
