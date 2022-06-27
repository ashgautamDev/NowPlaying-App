package com.ashish.nowplayingapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ashish.nowplayingapp.R
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.ui.components.MovieCard
import com.ashish.nowplayingapp.ui.components.PopularityDropDown
import com.ashish.nowplayingapp.ui.components.TopAppBar
import com.ashish.nowplayingapp.ui.states.ErrorCard
import com.ashish.nowplayingapp.ui.states.LoadingItem
import com.ashish.nowplayingapp.ui.states.LoadingView
import com.ashish.nowplayingapp.utils.ListState
import com.ashish.nowplayingapp.viewmodel.MainViewModel

const val TAG = "Home"

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                R.string.home_tagline,
                R.string.greeting_title,
                icon = Icons.Filled.Favorite
            ) {
                navController.navigate("fav")
            }
        }
    ) {
        MovieListView(viewModel = viewModel)
    }


}

@Composable
fun MovieListView(viewModel: MainViewModel) {

    val movieItems = viewModel.moviesData.collectAsLazyPagingItems()

    LaunchedEffect(key1 = viewModel.query.value) {
        Log.d(TAG, "On Launched effect ${viewModel.query.value}")
        movieItems.refresh()
    }

    LazyColumn {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PopularityDropDown(
                    onAllMovieClick = {
                        viewModel.query.value = ListState.ALL_PLAYING.string
                        Log.d(TAG, "On All Movie Clicked")
                        Log.d(TAG, "On All Movie Clicked ${ListState.ALL_PLAYING.string}")
                    },
                    onPopularMovieClick = {
                        viewModel.query.value = ListState.POPULAR_PLAYING.string
                        Log.d(TAG, "On Popular Movie Clicked")
                        Log.d(TAG, "On Popular Movie Clicked ${ListState.POPULAR_PLAYING.string}")
                    }

                )
            }
        }

        items(movieItems) { item ->

            MovieCard(movie = item!!) {
                //For Now just save on first click to db
                val favMovie = FavMovie(item.id)

                Log.d(TAG, " it value is $it")

                if (it) {
                    viewModel.addFavMovie(favMovie)
//                    val movie = viewModel.getMovieFromId(favMovie.id)
                    Log.d(
                        TAG,
                        "MovieListView: The movie ${item.original_title} with id ${item.id} is Favourite"
                    )
                } else {
                    viewModel.deleteFavMovie(favMovie)
                    Log.d(
                        TAG,
                        "MovieListView: ${item.original_title} with id ${item.id} It is Not favorite "
                    )
                }
            }


        }
        movieItems.apply {
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
                    val e = movieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorCard(message = e.error.localizedMessage!!)

                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = movieItems.loadState.append as LoadState.Error
                    item {
                        ErrorCard(message = e.error.localizedMessage!!)
                    }
                }
            }
        }
    }
}




