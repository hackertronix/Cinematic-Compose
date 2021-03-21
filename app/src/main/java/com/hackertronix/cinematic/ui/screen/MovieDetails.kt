package com.hackertronix.cinematic.ui.screen

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.hackertronix.cinematic.R
import com.hackertronix.cinematic.details.MovieDetailsViewModel
import com.hackertronix.cinematic.model.Cast
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.ui.RatingBar
import com.hackertronix.cinematic.util.Constants
import com.hackertronix.cinematic.util.FakeDataFactory.makeMovie

@Composable
fun MovieDetails(viewModel: MovieDetailsViewModel, movieId: Int) {
    viewModel.getMovieDetails(movieId)
    viewModel.getCast(movieId)

    val movie by viewModel.movie.observeAsState()
    val casts by viewModel.cast.observeAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter
    ) {
        MovieDetailsContent(movie, casts, LocalContext.current) { movie ->
            if (movie.isFavourite) {
                viewModel.unsetMovieAsFavourite(movie.id)
            } else {
                viewModel.setMovieAsFavourite(movie.id)
            }
        }
    }
}

@Composable
fun MovieDetailsContent(
    movie: Movie?,
    casts: List<Cast>?,
    context: Context,
    onFavoriteButtonClick: (Movie) -> Unit
) {
    if (movie == null) {
        return
    }

    MovieDetailsHeader(movie, context)
    MovieDetailsBody(movie, casts, onFavoriteButtonClick)
}

@Composable
fun MovieDetailsHeader(movie: Movie, context: Context) {
    Image(
        painter = rememberCoilPainter(
            request = Constants.IMAGE_BASE + movie.backdropPath,
            requestBuilder = {
                transformations(BlurTransformation(context, radius = 25f))
            },
            fadeIn = true
        ),
        contentDescription = null,
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun MovieDetailsBody(
    movie: Movie,
    casts: List<Cast>?,
    onFavoriteButtonClick: (Movie) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(top = 250.dp)
            .fillMaxHeight(),
        elevation = 8.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 650.dp)
                .padding(top = 120.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )

            RatingBar(
                currentRating = movie.rating, modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onSurface
            )

            SectionHeader(text = "Overview")

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            CastsRow(casts)

            AddToFavouritesButton(
                movie = movie,
                Modifier.padding(vertical = 32.dp),
                onFavoriteButtonClick
            )

        }
    }

    DetailsPoster(movie)

}

@Composable
fun CastsRow(casts: List<Cast>?) {
    casts?.let {
        SectionHeader(text = "Casts")

        LazyRow(contentPadding = PaddingValues(end = 24.dp)) {
            items(it) {
                CastItem(it.profilePath)
            }
        }
    }
}

@Composable
fun CastItem(profilePath: String) {
    Image(
        painter = rememberCoilPainter(
            request = Constants.IMAGE_BASE + profilePath,
            requestBuilder = {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .padding(start = 24.dp)
            .size(60.dp),
    )
}


@Composable
fun AddToFavouritesButton(
    movie: Movie,
    modifier: Modifier = Modifier,
    onFavoriteButtonClick: (Movie) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(32.dp),
            text = {
                Text(
                    text = if (movie.isFavourite) stringResource(id = R.string.remove_from_favourites)
                    else stringResource(id = R.string.add_to_favourites),
                    style = MaterialTheme.typography.button
                )
            },
            icon = {
                Icon(
                    imageVector = if (movie.isFavourite) Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder, contentDescription = null
                )
            },
            onClick = {
                onFavoriteButtonClick(movie)
            },
        )
    }
}

@Composable
fun SectionHeader(text: String) {
    Spacer(modifier = Modifier.padding(top = 32.dp))

    Text(
        style = MaterialTheme.typography.subtitle2,
        text = text,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    )
}

@Composable
fun DetailsPoster(movie: Movie) {
    Surface(
        modifier = Modifier
            .padding(top = 142.dp)
            .size(150.dp, 210.dp),
        border = BorderStroke(4.dp, Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = 12.dp
    ) {
        Image(
            painter = rememberCoilPainter(
                request = Constants.IMAGE_BASE + movie.posterPath,
                fadeIn = true
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
fun previewMovieDetails() {
    MovieDetailsContent(movie = makeMovie(), casts = emptyList(), context = LocalContext.current) {

    }
}