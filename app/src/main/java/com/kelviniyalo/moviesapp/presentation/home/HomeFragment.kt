package com.kelviniyalo.moviesapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.kelviniyalo.moviesapp.R
import com.kelviniyalo.moviesapp.adapter.RecentMoviesAdapter
import com.kelviniyalo.moviesapp.adapter.SearchMoviesAdapter
import com.kelviniyalo.moviesapp.data.model.MoviesResponse
import com.kelviniyalo.moviesapp.databinding.FragmentHomeBinding
import com.kelviniyalo.moviesapp.presentation.search_movie.SearchMovieFragmentDirections
import com.kelviniyalo.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding
    private val viewModel:MoviesViewModel by viewModels()
    private lateinit var recentMoviesAdapter: RecentMoviesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        manageRecyclerViewItems()
       binding.searchEt.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.toolbarSection to "search_toolbar",
                binding.searchEt to "search_search"
            )

            val direction = R.id.action_navigation_home_to_navigation_search_movie
            findNavController().navigate(
                direction,
                null,
                null,
                extras
            )
        }

        viewModel.getAllSavedFromDb().observe(viewLifecycleOwner, Observer { response ->
            val isListEmpty = response.isEmpty()
            if (!isListEmpty){
                binding.viewedList.isVisible = !isListEmpty
                recentMoviesAdapter.submitList(response)
            }else{
                binding.emptyList.isVisible = isListEmpty
            }
        })
    }


    private fun manageRecyclerViewItems(){
        recentMoviesAdapter = RecentMoviesAdapter(
            onItemClicked = { position: Int, itemAtPosition ->
                manageItemClick(response = itemAtPosition)
            }
        )
        binding.searchListRv.adapter = recentMoviesAdapter
    }

    private fun manageItemClick(response: MoviesResponse) {
        val direction =
            HomeFragmentDirections.actionNavigationHomeToNavigationMovieDetails(
                null,
                response
            )
        findNavController().navigate(
            direction
        )
    }
}