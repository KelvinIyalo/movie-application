package com.kelviniyalo.moviesapp.presentation.movie_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kelviniyalo.moviesapp.R
import com.kelviniyalo.moviesapp.common.Helper
import com.kelviniyalo.moviesapp.common.Helper.loadImage
import com.kelviniyalo.moviesapp.common.response_handler.UiState
import com.kelviniyalo.moviesapp.data.model.MoviesResponse
import com.kelviniyalo.moviesapp.databinding.FragmentMovieDetailsBinding
import com.kelviniyalo.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MoviesViewModel by viewModels()
    val navigationArgs: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        getMovieDetails()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.navigation_home, true)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun getMovieDetails() {
        if (navigationArgs.movieId!!.isNotEmpty()) {
            searchRemoteSourceById(navigationArgs.movieId.toString())
        } else {
            getFromLocalSource()
        }
    }

    private fun getFromLocalSource() {
        manageUiComponent(navigationArgs.movieCachedResponse!!)
    }

    private fun searchRemoteSourceById(title: String) {
        viewModel.getMovieById(title).observe(viewLifecycleOwner, Observer { response ->
            when (response) {

                is UiState.Loading -> {
                    showLoader(true)
                }

                is UiState.Success -> {
                    showLoader(false)
                    manageUiComponent(response.data)
//                    viewModel.saveToDb(response.data)
                }

                is UiState.DisplayError -> {
                    showLoader(false)
                    showErrorMessage(response.error)
                }
            }
        })
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showLoader(isLoading: Boolean) {
        binding.loader.isVisible = isLoading
    }

    private fun manageUiComponent(movieResponse: MoviesResponse) {
        with(binding) {
            if (navigationArgs.movieId != null) {
                movieImg.loadImage(movieResponse.Poster, requireContext()) {
                    val result = movieResponse.copy(Image = Helper.bitmapToByteArray(it))
                    viewModel.saveToDb(result)
                    Log.d("XXXX Item Saved",result.toString())
                }
            } else {
                val imageResult = Helper.byteArrayToBitmap(movieResponse.Image!!)
                movieImg.setImageBitmap(imageResult)
            }
            movieTittle.text = movieResponse.Title
            plotValue.text = movieResponse.Plot
            movieYear.text = buildString {
                append("Released: " + movieResponse.Released)
                append(" - ")
                append("Genre: " + movieResponse.Genre)
            }
            actorsValue.text = movieResponse.Actors
            directorValue.text = movieResponse.Director
        }
    }


}