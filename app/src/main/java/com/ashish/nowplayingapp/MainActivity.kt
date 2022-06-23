package com.ashish.nowplayingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ashish.nowplayingapp.ui.screen.HomeScreen
import com.ashish.nowplayingapp.viewmodel.MainViewModel
import dagger.hilt.EntryPoint

@EntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            HomeScreen(viewModel)
        }
    }
}
