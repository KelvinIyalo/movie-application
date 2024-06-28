package com.kelviniyalo.moviesapp.presentation.search_movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kelviniyalo.moviesapp.R
import com.kelviniyalo.moviesapp.databinding.FragmentSearchBinding

class SearchMovieFragment : Fragment(R.layout.fragment_search) {

   private lateinit var binding: FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

    }
}