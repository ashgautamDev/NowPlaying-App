package com.ashish.nowplayingapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.ui.theme.NowPlayingAppTheme
import com.ashish.nowplayingapp.ui.theme.accent
import com.google.accompanist.coil.rememberCoilPainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(movie: Movie, isMovieFav: Boolean = false, onFavClick: (Boolean) -> Unit) {

    var isFavMovie by remember { mutableStateOf(isMovieFav) }
    val context = LocalContext.current
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(8.dp)
            .clip(MaterialTheme.shapes.large),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            isExpanded = !isExpanded
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 0.dp, 16.dp)
            ) {

                val image = rememberCoilPainter(
                    request = "https://image.tmdb.org/t/p/w300${movie.backdrop_path}",
                    imageLoader = ImageLoader.invoke(context)
                )
                Image(
                    modifier = Modifier
                        .size(110.dp, 140.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = image,
                    alignment = Alignment.CenterStart,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        // Title of Movie
                        Text(
                            text = movie.original_title,
                            modifier = Modifier.weight(3f),
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = FontWeight.Bold,
                            style = typography.h5,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Heart Icon for is movie fav or not
                        IconToggleButton(
                            checked = isFavMovie,
                            onCheckedChange = {
                                isFavMovie = !isFavMovie
                                onFavClick(isFavMovie)
                            }
                        ) {
                            Icon(
                                tint = accent.copy(alpha = .7f),

                                imageVector = if (isFavMovie) {
                                    Icons.Filled.Favorite
                                } else {
                                    Icons.Default.FavoriteBorder
                                },
                                contentDescription = null
                            )
                        }

                    }

//                Spacer(modifier = Modifier.height(8.dp))

                    RatingBarView(averageVote = movie.vote_average)
//                Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = buildString {
                            append("Popularity - ")
                            append(movie.popularity)
                        },
                        modifier = Modifier,
                        color = MaterialTheme.colors.onPrimary,
                        style = typography.body1
                    )


                }

            }

            if (isExpanded) {
                Text(
                    text = movie.original_title,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold,
                    style = typography.h5,

                    )
                Text(
                    text = buildString {
                        append("Overview -")
                        append(movie.overview)
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    color = MaterialTheme.colors.onPrimary,
                    style = typography.body1
                )
            }


        }


    }
}

@Preview
@Composable
fun MovieCardPreview() {

    NowPlayingAppTheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            MovieCard(movie = Movie.sampleMovie()) {

            }
        }

    }

}