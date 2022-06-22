package com.ashish.nowplayingapp.utils

import com.ashish.nowplayingapp.model.Movie


sealed class ViewState {


    // It Represents different states for Now Playing and popular
    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val movies: List<Movie>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()

}
