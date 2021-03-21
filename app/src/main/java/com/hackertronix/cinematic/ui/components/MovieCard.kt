package com.hackertronix.cinematic.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.util.*
import com.hackertronix.cinematic.util.FakeDataFactory.makeMovie

@Composable
fun MovieCard(movie: Movie, onMovieClicked: (Movie) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onMovieClicked.invoke(movie)
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = rememberCoilPainter(
                request = Constants.IMAGE_BASE + movie.backdropPath,
                requestBuilder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black),
                        0f,
                        500f,
                    )
                )
        ) {}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = movie.movieInfo,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            RatingBar(
                currentRating = movie.rating,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RatingBar(
    range: IntRange = 1..5,
    currentRating: Float,
    modifier: Modifier,
    color: Color = Color.White
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(top = 8.dp)) {
        Text(
            text = currentRating.toString(),
            color = color,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(end = 8.dp)
        )
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(range.toList()) { value ->
                RatingItem(value = value, currentRating = currentRating)
            }
        }
    }
}

@Composable
fun RatingItem(value: Int, currentRating: Float) {
    val (whole, fraction) = currentRating.splitToWholeAndFraction()
    val imageVector = if (value.toFloat() == whole + 1 && fraction in 0.5..0.9) {
        Icons.Default.StarHalf
    } else Icons.Default.Star

    val color = if (value <= whole || value.toFloat() == whole + 1 && fraction in 0.5..0.9) {
        Color.Yellow
    } else Color.Gray

    Icon(
        imageVector = imageVector,
        contentDescription = null,
        tint = color
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun previewMovieCard() {
    MovieCard(movie = makeMovie(), onMovieClicked = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun previewMovieCardDark() {
    MovieCard(movie = makeMovie(), onMovieClicked = {})
}