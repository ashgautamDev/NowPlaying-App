package com.ashish.nowplayingapp.ui.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashish.nowplayingapp.R
import com.ashish.nowplayingapp.ui.theme.NowPlayingAppTheme
import com.ashish.nowplayingapp.ui.theme.Typography
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen() {
    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(2f)
    }

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.Main) {
            scale.animateTo(
                targetValue = 0.5f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        overshootInterpolator.getInterpolation(it)
                    }
                )
            )

            delay(2000L)

//            navController.navigate("") {
//                this.launchSingleTop
//            }
        }

    }

    Card(
    ) {
        Image(
            painterResource(id = R.drawable.splash_background), contentDescription = null,
            modifier = Modifier.fillMaxSize() , contentScale = ContentScale.FillHeight
        )
        Column(Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center) {
            Text(
            text = "NOW PLAYING \n APP", color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.None,
            style = Typography.h4,
                textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
            ,

        )
        }

    }

}

@Preview
@Composable
fun Splashprv() {

    NowPlayingAppTheme() {

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            SplashScreen()
        }
    }
}
