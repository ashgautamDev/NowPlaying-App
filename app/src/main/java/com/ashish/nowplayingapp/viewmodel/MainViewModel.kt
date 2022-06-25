package com.ashish.nowplayingapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashish.nowplayingapp.data.paging.MoviePagingSource
import com.ashish.nowplayingapp.data.repository.FavMovieRepository
import com.ashish.nowplayingapp.data.repository.MovieRepository
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.utils.FavViewState
import com.ashish.nowplayingapp.utils.MovieState
import com.ashish.nowplayingapp.utils.ViewState
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
    private val _nowPlayingState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _favState = MutableStateFlow<FavViewState>(FavViewState.Loading)

    // UI collects from this StateFlow to get it"s state update
    val nowPlayingState = _nowPlayingState.asStateFlow()
    val favState = _favState.asStateFlow()


    private var _movieState = mutableStateOf(false)
    val movieState = _movieState

    val nowPlayingMovies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 10)) {
        MoviePagingSource(movieRepository)
    }.flow

    val popularMovies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 10)) {
        MoviePagingSource(movieRepository)
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

    fun getMovie(id: Long) = viewModelScope.launch {
        movieRepository.getMovie(id)
    }

    // add to favourites by taking id
    fun addFavMovie(movie: FavMovie) = viewModelScope.launch {
        favMovieRepository.insertFavMovie(movie)
    }

    // deleting from db
    fun deleteFavMovie(movie: FavMovie) = viewModelScope.launch {
        favMovieRepository.deleteFavMovie(movie)
    }

//    fun getMovieFromId(favMovie: FavMovie) = getMovie(favMovie)

    fun setListToPopular(){
        _movieState.value = true
    }

}
