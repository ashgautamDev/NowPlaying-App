package com.ashish.nowplayingapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
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
import com.ashish.nowplayingapp.utils.MovieState
import com.ashish.nowplayingapp.viewmodel.MainViewModel

const val TAG = "Home"

@Composable
fun HomeScreen(viewModel: MainViewModel , navController: NavController) {
    var popularIndex by remember { mutableStateOf(1) }


//    if (indexFilter == 0) {
//        Log.d(TAG, "MovieListView: we are showing Most Popular Movies ")
//    } else {
//        Log.d(TAG, "MovieListView: We are showing the normal playing movies")
//    }

    Scaffold(
        topBar = {
            TopAppBar(R.string.home_tagline ,R.string.greeting_title, icon = Icons.Filled.Favorite){
                navController.navigate("fav")
            }
        }
    ) {
        MovieListView(viewModel = viewModel)
    }


}

@Composable
fun MovieListView(viewModel: MainViewModel) {

    val lazyMovieItems = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val lazyPopularMovieItems = viewModel.popularMovies.collectAsLazyPagingItems()

    val movieItems = if (!viewModel.movieState.value) lazyMovieItems else lazyPopularMovieItems


    LazyColumn {
        item {
            Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            PopularityDropDown(){
                viewModel.setListToPopular()
            }
            } }
        items(movieItems) { item ->

            MovieCard(movie = item!!) {
                //For Now just save on first click to db
                val favMovie = FavMovie(item.id)
                viewModel.addFavMovie(favMovie)
                if (it) {
                    Log.d(
                        TAG,
                        "MovieListView: The movie ${item.original_title} with id ${item.id} is Favourite "
                    )

                }else Log.d(TAG, "MovieListView: ${item.original_title} with id ${item.id} It is Not favorite ")
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




