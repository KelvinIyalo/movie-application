package com.kelviniyalo.moviesapp.presentation.search_movie

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kelviniyalo.moviesapp.R
import com.kelviniyalo.moviesapp.adapter.SearchMoviesAdapter
import com.kelviniyalo.moviesapp.common.response_handler.UiState
import com.kelviniyalo.moviesapp.databinding.FragmentSearchBinding
import com.kelviniyalo.moviesapp.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMovieFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchMoviesAdapter: SearchMoviesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        initTransactionsRecyclerViewAdapter()
        with(binding) {

            searchEt.apply {
                requestFocus()
                setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        val inputMethodManager =
                            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
                    }
                }
                addTextChangedListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000)
                        searchMovies(it.toString().trim())
                    }
                }
            }
            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun searchMovies(title: String) {
        viewModel.searchMovies(title).observe(viewLifecycleOwner, Observer { response ->
            when (response) {

                is UiState.Loading -> {
                    showLoader(true)
                }

                is UiState.Success -> {
                    showLoader(false)
                    searchMoviesAdapter.submitList(response.data)
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

    private fun initTransactionsRecyclerViewAdapter() {
        searchMoviesAdapter = SearchMoviesAdapter(
            requireContext(),
            onItemClicked = { position: Int, itemAtPosition ->
                manageItemClick(itemId = itemAtPosition.imdbID)
            }
        )
        binding.searchListRv.adapter = searchMoviesAdapter
    }

    private fun manageItemClick(itemId: String) {
        val direction =
            SearchMovieFragmentDirections.actionNavigationSearchMovieToNavigationMovieDetails(
                itemId,
                null
            )
        findNavController().navigate(
            direction
        )
    }
}