package com.ashish.nowplayingapp.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.ashish.nowplayingapp.R
import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.ui.components.MovieCard
import com.ashish.nowplayingapp.ui.components.TopAppBar
import com.ashish.nowplayingapp.ui.states.EmptyListCard
import com.ashish.nowplayingapp.ui.states.ErrorCard
import com.ashish.nowplayingapp.ui.states.LoadingItem
import com.ashish.nowplayingapp.utils.FavViewState
import com.ashish.nowplayingapp.viewmodel.MainViewModel

const val T = "Fav"

@Composable
fun FavScreen(viewModel: MainViewModel, navController: NavController) {

    Scaffold(

        topBar = {
            TopAppBar(
                R.string.fav_tagline,
                R.string.favscreen_title,
                icon = Icons.Filled.ArrowBack
            ) {
                navController.navigate("home") {
                    this.launchSingleTop
                }
            }
        }

    ) {
        when (val result = viewModel.favState.collectAsState().value) {
            FavViewState.Loading -> {
                LoadingItem()
            }

            FavViewState.Empty -> {
                EmptyListCard {
                    navController.navigate("home")
                }
            }
            is FavViewState.Success -> {
                FavMoviesList(moviesIdList = result.movies, viewModel)
            }

            is FavViewState.Error -> {
                ErrorCard(message = result.exception.localizedMessage!!)
            }

        }

    }


}

@Composable
fun FavMoviesList(moviesIdList: List<Movie>, viewModel: MainViewModel) {

    LazyColumn() {
        items(moviesIdList) { movie ->

            MovieCard(movie = movie, isMovieFav = true) {
                viewModel.deleteFavMovie(movie)
            }
        }
    }


}






