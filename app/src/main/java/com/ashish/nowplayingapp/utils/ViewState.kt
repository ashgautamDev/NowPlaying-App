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

enum class ListState(val string : String) {
    POPULAR_PLAYING("popular"),
    ALL_PLAYING("now_playing")

}
