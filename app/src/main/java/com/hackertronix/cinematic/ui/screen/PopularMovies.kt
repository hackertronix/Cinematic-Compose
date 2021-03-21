package com.hackertronix.cinematic.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.popular.PopularMoviesViewModel

@Composable
fun PopularMovies(viewModel: PopularMoviesViewModel, onMovieClicked: (Movie) -> Unit) {
    val popularMovies by viewModel.movies.observeAsState()

    LazyColumn(contentPadding = PaddingValues(bottom = 32.dp)) {
        popularMovies?.let {
            items(it) { movie ->
                MovieCard(movie = movie, onMovieClicked)
            }
        }
    }
}