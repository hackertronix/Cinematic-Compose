package com.hackertronix.cinematic.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.hackertronix.cinematic.R
import com.hackertronix.cinematic.databinding.FragmentDetailsBinding
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.util.Constants.IMAGE_BASE
import com.hackertronix.cinematic.util.convertToFiveStarScale
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetails(args.movieId)
        attachObservers()
    }

    private fun attachObservers() {
        viewModel.movie.observe(viewLifecycleOwner, Observer { movie ->
            renderUi(movie)
        })
    }

    private fun renderUi(movie: Movie) {
        binding.backdrop.load(IMAGE_BASE + movie.backdropPath) {
            crossfade(true)
            transformations(BlurTransformation(requireContext()))
        }
        binding.poster.load(IMAGE_BASE + movie.posterPath) {
            crossfade(true)
        }

        binding.title.text = movie.title
        binding.summary.text = movie.overview
        binding.ratingValue.text = movie.voteAverage.convertToFiveStarScale().toString()
        binding.movieRating.rating = movie.voteAverage.convertToFiveStarScale()

        binding.addToFavourites.apply {
            icon = if (movie.isFavourite) {
                getDrawable(requireContext(), R.drawable.ic_baseline_favorite_24)
            } else {
                getDrawable(requireContext(), R.drawable.ic_baseline_favorite_border_24)
            }
            text = if (movie.isFavourite) {
                getString(R.string.remove_from_favourites)
            } else {
                getString(R.string.add_to_favourites)
            }
            setOnClickListener {
                if (movie.isFavourite) {
                    viewModel.unsetMovieAsFavourite(movie.id)
                } else {
                    viewModel.setMovieAsFavourite(movie.id)
                }
            }
        }
    }
}