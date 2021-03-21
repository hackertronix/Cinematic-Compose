package com.hackertronix.cinematic.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hackertronix.cinematic.R
import com.hackertronix.cinematic.ui.screen.MovieDetails
import com.hackertronix.cinematic.ui.theme.CinematicTheme
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                CinematicTheme {
                    MovieDetails(viewModel = viewModel, movieId = args.movieId)
                }
            }
        }
    }

}