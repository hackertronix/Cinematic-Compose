package com.hackertronix.cinematic.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hackertronix.cinematic.MoviesAdapter
import com.hackertronix.cinematic.ui.PopularMovies
import com.hackertronix.cinematic.ui.theme.CinematicTheme
import org.koin.android.ext.android.inject

class PopularMoviesFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by inject()
    private val popularAdapter: MoviesAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                CinematicTheme {
                    PopularMovies(viewModel = viewModel, onMovieClicked = { movie ->
                        findNavController().navigate(
                            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(
                                movie.id
                            )
                        )
                    })
                }
            }
        }
    }
}