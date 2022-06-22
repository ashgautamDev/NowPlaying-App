package com.ashish.nowplayingapp.utils

import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.model.Movie


sealed class FavViewState {


    // It Represents different states for Now Playing and popular
    object Empty : FavViewState()
    object Loading : FavViewState()
    data class Success(val movies: List<FavMovie>) : FavViewState()
    data class Error(val exception: Throwable) : FavViewState()

}
