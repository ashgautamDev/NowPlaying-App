package com.ashish.nowplayingapp.ui.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.ashish.nowplayingapp.R
import com.ashish.nowplayingapp.ui.theme.NowPlayingAppTheme
import com.ashish.nowplayingapp.ui.theme.Typography

@Composable
fun ErrorCard(message : String) {

    val isPlaying by remember {
        mutableStateOf(true)
    }

    val speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.error_anim)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = true
    )

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            progress = progress
        )

        Text(
            text = message,
            modifier = Modifier.padding(0.dp, 0.dp, 6.dp, 0.dp),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6
        )

    }


}


