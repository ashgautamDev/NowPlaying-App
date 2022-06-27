package com.ashish.nowplayingapp.utils

import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie


sealed class FavViewState {


    // It Represents different states for Now Playing and popular
    object Empty : FavViewState()
    object Loading : FavViewState()
    data class Success(val movies: List<Movie>) : FavViewState()
    data class Error(val exception: Throwable) : FavViewState()

}

sealed class MovieState {

    // It Represents different states for Now Playing and popular
    object Empty : MovieState()
    object Loading : MovieState()
    data class Success(val movies: Movie) : MovieState()
    data class Error(val exception: Throwable) : MovieState()

}


