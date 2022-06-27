package com.ashish.nowplayingapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashish.nowplayingapp.ui.screen.FavScreen
import com.ashish.nowplayingapp.ui.screen.HomeScreen
import com.ashish.nowplayingapp.ui.screen.SplashScreen
import com.ashish.nowplayingapp.viewmodel.MainViewModel
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory


@Composable
fun NavGraph(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
          SplashScreen(navController)
        }
        composable("home") {
            HomeScreen(viewModel , navController)
        }
        composable("fav") {
            FavScreen(viewModel , navController)
        }
    }
}