package com.hackertronix.cinematic.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hackertronix.cinematic.R
import com.hackertronix.cinematic.ui.FavoriteMovies
import com.hackertronix.cinematic.ui.theme.CinematicTheme
import org.koin.android.ext.android.inject

class FavoriteMoviesFragment : Fragment(R.layout.fragment_favorites) {
    private val viewModel: FavouriteMoviesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                CinematicTheme {
                    FavoriteMovies(viewModel = viewModel, onMovieClicked = { movie ->
                        findNavController().navigate(
                            FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(
                                movie.id
                            )
                        )
                    })
                }
            }
        }
    }
}