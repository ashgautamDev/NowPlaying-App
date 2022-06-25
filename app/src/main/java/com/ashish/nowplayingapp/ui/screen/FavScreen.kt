package com.ashish.nowplayingapp.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.ashish.nowplayingapp.R
import com.ashish.nowplayingapp.model.FavMovie
import com.ashish.nowplayingapp.ui.components.MovieCard
import com.ashish.nowplayingapp.ui.components.TopAppBar
import com.ashish.nowplayingapp.ui.states.EmptyListCard
import com.ashish.nowplayingapp.ui.states.ErrorCard
import com.ashish.nowplayingapp.ui.states.LoadingItem
import com.ashish.nowplayingapp.utils.FavViewState
import com.ashish.nowplayingapp.viewmodel.MainViewModel

const val T = "Fav"

@Composable
fun FavScreen(viewModel: MainViewModel , navController: NavController) {
    var indexFilter by remember { mutableStateOf(1) }
    var indexList by remember { mutableStateOf(1) }

//    if (indexFilter == 0) {
//        Log.d(TAG, "MovieListView: we are showing Most Popular Movies ")
//    } else {
//        Log.d(TAG, "MovieListView: We are showing the normal playing movies")
//    }

    Scaffold(

        topBar = {
            TopAppBar(R.string.fav_tagline , R.string.favscreen_title , icon = Icons.Filled.ArrowBack){
                navController.navigate("home"){
                    this.launchSingleTop
                }
            }
        }

    ) {
        when(val result = viewModel.favState.collectAsState().value){
            is FavViewState.Success ->{
                FavMoviesList(moviesIdList = result.movies , viewModel)
            }
            FavViewState.Empty -> {
                EmptyListCard {
                    navController.navigate("home")
                }
            }

            is FavViewState.Error -> {
                ErrorCard(message = result.exception.localizedMessage!!)
            }
            FavViewState.Loading -> {
                LoadingItem()
            }
        }

    }


}

@Composable
fun FavMoviesList(moviesIdList : List<FavMovie> , viewModel: MainViewModel) {

    LazyColumn(){
        items(moviesIdList){
//            MovieCard(movie = movie.,){
//
//            }
        }
    }


}






