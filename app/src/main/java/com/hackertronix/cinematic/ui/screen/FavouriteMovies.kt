package com.hackertronix.cinematic.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.hackertronix.cinematic.favorites.FavouriteMoviesViewModel
import com.hackertronix.cinematic.model.Movie

@Composable
fun FavoriteMovies(viewModel: FavouriteMoviesViewModel, onMovieClicked: (Movie) -> Unit) {
    val favouriteMovies by viewModel.movies.observeAsState()

    LazyColumn {
        favouriteMovies?.let {
            items(it) { movie ->
                MovieCard(movie = movie, onMovieClicked)
            }
        }
    }
}