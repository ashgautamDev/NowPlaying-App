package com.ashish.nowplayingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ashish.nowplayingapp.navigation.NavGraph
import com.ashish.nowplayingapp.ui.theme.NowPlayingAppTheme
import com.ashish.nowplayingapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NowPlayingAppTheme {
                NavGraph(viewModel)
            }
        }
    }
}
