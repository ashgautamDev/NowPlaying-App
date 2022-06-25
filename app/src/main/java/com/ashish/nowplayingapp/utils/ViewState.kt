package com.ashish.nowplayingapp.utils

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.ashish.nowplayingapp.model.Movie
import kotlinx.coroutines.flow.Flow


sealed class ViewState {


    // It Represents different states for Now Playing and popular
    object Empty : ViewState()
    object Loading : ViewState()
    data class Success(val movies: List<Movie>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()

}
sealed class MovieState {


    object Loading : MovieState()
    data class Popular(val movies: Flow<PagingData<Movie>>) : MovieState()
    data class Common(val movies: Flow<PagingData<Movie>>) : MovieState()

}
