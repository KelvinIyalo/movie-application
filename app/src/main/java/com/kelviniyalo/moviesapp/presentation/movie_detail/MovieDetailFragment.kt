package com.kelviniyalo.moviesapp.presentation.movie_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kelviniyalo.moviesapp.R
import com.kelviniyalo.moviesapp.databinding.FragmentMovieDetailsBinding

class MovieDetailFragment:Fragment(R.layout.fragment_movie_details) {

    private lateinit var binding: FragmentMovieDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

    }
}