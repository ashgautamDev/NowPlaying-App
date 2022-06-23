package com.ashish.nowplayingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.ashish.nowplayingapp.model.Movie
import com.ashish.nowplayingapp.ui.theme.NowPlayingAppTheme
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun MovieCard(movie: Movie, onFavClick: (Boolean) -> Unit) {

    val isFavMovie = remember {
        mutableStateOf(false)
    }

    var favIcon = Icons.Default.FavoriteBorder
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.large),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val image = rememberCoilPainter(
                request = "https://image.tmdb.org/t/p/w300${movie.backdrop_path}",
                imageLoader = ImageLoader.invoke(context)
            )
            Image(
                modifier = Modifier
                    .size(110.dp, 130.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = image,
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {

                Row(verticalAlignment = Alignment.Bottom) {

                    // Title of Movie
                    Text(
                        text = movie.original_title,
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Bold,
                        style = typography.h5
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Heart Icon for is movie fav or not
                    IconButton(onClick = {
                        favIcon = Icons.Filled.Favorite
                        onFavClick(isFavMovie.value)
                    }, modifier = Modifier.align(Alignment.Bottom)) {
                        Icon(imageVector = favIcon, contentDescription = "Fav Button")
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                RatingBarView(averageVote = movie.vote_average)
                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = buildString {
                        append("Popularity - ")
                        append(movie.popularity)
                    },
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
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

    NowPlayingAppTheme() {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            MovieCard(movie = Movie.sampleMovie()) {

            }
        }

    }

}