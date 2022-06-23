package com.ashish.nowplayingapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.ui.components.ListDropDown
import com.ashish.nowplayingapp.ui.components.MovieCard
import com.ashish.nowplayingapp.ui.components.PopularityDropDown
import com.ashish.nowplayingapp.ui.components.TopAppBar
import com.ashish.nowplayingapp.ui.states.ErrorCard
import com.ashish.nowplayingapp.ui.states.LoadingItem
import com.ashish.nowplayingapp.ui.states.LoadingView
import com.ashish.nowplayingapp.viewmodel.MainViewModel

const val TAG = "Home"

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    var indexFilter by remember { mutableStateOf(1) }
    var indexList by remember { mutableStateOf(1) }

//    if (indexFilter == 0) {
//        Log.d(TAG, "MovieListView: we are showing Most Popular Movies ")
//    } else {
//        Log.d(TAG, "MovieListView: We are showing the normal playing movies")
//    }

    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {

        MovieListView(viewModel = viewModel)


    }


}

@Composable
fun MovieListView(viewModel: MainViewModel) {

    val lazyMovieItems = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ListDropDown(index = 1)
                PopularityDropDown(index = 1)

            }
        }

        items(lazyMovieItems) { item ->

            MovieCard(movie = item!!) {
                //For Now just save on first click to db

                if (it) Log.d(
                    TAG,
                    "MovieListView: The movie ${item.original_title} with id ${item.id} is Favourite "
                ) else Log.d(TAG, "MovieListView: It is Not favorite ")
                val favMovie = FavMovie(item.id)
                viewModel.addFavMovie(favMovie)

            }

        }
        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingView(modifier = Modifier.fillParentMaxSize())
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorCard(message = e.error.localizedMessage!!)

                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorCard(message = e.error.localizedMessage!!)
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeScreenPrv() {
//
//
//    NowPlayingAppTheme() {
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//        ) {
//
//        }
//    }
//
//}



